package com.more.forhelp.impl;

import com.alibaba.fastjson.JSONObject;
import com.more.forhelp.entity.login.User;
import com.more.forhelp.init.login.UserLoginService;
import com.more.forhelp.mapper.login.UserLoginMapper;
import com.more.forhelp.until.RSAUntil;
import com.more.forhelp.until.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserLoginMapper userLoginMapper;

    @Value("${RSA.publicKey}")
    private String publicKey;

    @Value("${RSA.privateKey}")
    private String privateKey;

    SimpleDateFormat sdf = new SimpleDateFormat("“yyyy-MM-dd HH:mm:ss”");

    @Override
    public Result<String> userRegister(String param) {
        Result<String> result = new Result<>();
        Map<String,String> jsonObject = (Map<String,String>) JSONObject.parse(param);
        String tel = jsonObject.get("tel");
        String code = jsonObject.get("code");
        String userName = jsonObject.get("userName");
        String passWord = jsonObject.get("passWord");
        //TODO 验证码

        try {
            // 密码加密
            String rsaPassWord = RSAUntil.encrypt(passWord,publicKey);
            User user = new User();
            user.setUserName(userName);
            user.setPassWord(rsaPassWord);
            user.setPhoneNum(tel);
            user.setUserStatus(0);
            user.setRegisterDate(sdf.format(new Date()));

            userLoginMapper.insert(user);
        } catch (Exception e) {
            result.setCode(001);
            result.setMsg("服务器异常");
        }
        return result;
    }

    @Override
    public Result<String> checkUserName(String param) {
        Result<String> result = new Result<>();
        Map<String,String> jsonObject = (Map<String,String>) JSONObject.parse(param);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userName",jsonObject.get("userName"));
        try {
            List<User> list = userLoginMapper.selectByMap(paramMap);
            if (list.size()>0){
                result.setData("9999");
            } else {
                result.setData("0000");
            }
        } catch (Exception e) {
            result.setCode(001);
            result.setMsg("服务器异常");
        }

        return result;
    }

    @Override
    public Result<String> userLogin(String param) {
        Result<String> result = new Result<>();
        Map<String,String> jsonObject = (Map<String,String>) JSONObject.parse(param);
        String tel = jsonObject.get("tel");
        String code = jsonObject.get("code");
        String userName = jsonObject.get("userName");
        String passWord = jsonObject.get("passWord");
        String loginType = jsonObject.get("loginType");

        try {
            if ("密码登录".equals(loginType)){
                //TODO 验证码

            } else {
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("userName",userName);
                List<User> list = userLoginMapper.selectByMap(paramMap);
                if (list.size()>0){
                    User user = list.get(0);
                    String DePassWord = RSAUntil.decrypt(user.getPassWord(),privateKey);
                    if (passWord.equals(DePassWord)){
                        if ("1".equals(user.getUserStatus())){
                            result.setCode(003);
                            result.setMsg("账户状态异常");
                        } else {
                            result.setData("ok");
                        }
                    } else {
                        result.setCode(002);
                        result.setMsg("密码错误");
                    }
                } else {
                    result.setCode(002);
                    result.setMsg("用户不存在");
                }
            }
        } catch (Exception e) {
            result.setCode(001);
            result.setMsg("服务器异常");
        }
        return result;
    }

}

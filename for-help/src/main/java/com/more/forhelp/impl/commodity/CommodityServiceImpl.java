package com.more.forhelp.impl.commodity;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.SftpException;
import com.more.forhelp.entity.commodity.Commodity;
import com.more.forhelp.init.commodity.CommodityService;
import com.more.forhelp.mapper.commodity.CommodityMapper;
import com.more.forhelp.until.Result;
import com.more.forhelp.until.SFTPUtil;
import com.more.forhelp.until.SnowFlakeUntil;
import com.more.forhelp.vo.commodity.CommodityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    SnowFlakeUntil snowFlakeUntil;



    @Override
    public Result<List<CommodityVo>> getCommodityInfo(String param) {
        Map<String,String> jsonObject = (Map<String,String>) JSONObject.parse(param);
        String activityCode = jsonObject.get("activityCode");
        String platformCode = jsonObject.get("platformCode");
        Result<List<CommodityVo>> result = new Result();
        try {
            List<Commodity> list = new ArrayList<>();
            if (StringUtils.equals(platformCode,"null")){
                list = commodityMapper.selectList(null);
            } else if (!StringUtils.equals(platformCode,"null")){
                Map paramMap = new HashMap();
                if (!StringUtils.equals(activityCode,"null")){
                    paramMap.put("activityCode",activityCode);
                }
                paramMap.put("platformCode",platformCode);
                list = commodityMapper.selectByMap(paramMap);
            }
            List<CommodityVo> commodityVoList = new ArrayList<>();
            list.forEach(commodity -> {
                CommodityVo commodityVo = new CommodityVo();
                commodityVo.setPrice(String.valueOf(commodity.getPrice()));
                commodityVo.setDesc(commodity.getCommodityDesc());
                commodityVo.setTitle(commodity.getCommodityName());
                commodityVo.setThumb("for-help/commodity/readImg?url="+commodity.getUrl());
                commodityVoList.add(commodityVo);
            });
            result.setData(commodityVoList);
        } catch (Exception e) {
            result.setCode(000);
            result.setMsg("服务端异常");
        }
        return result;
    }

    @Override
    public Result<String> uploadImg(MultipartFile param) {
        Result<String> result = new Result<>();
        try {
            // 雪花算法生成文件名
            String fileKey = String.valueOf(snowFlakeUntil.nextId());
            String oldName = param.getOriginalFilename();
            String newName = fileKey+oldName.substring(oldName.indexOf("."));
            // 上传图片到sftp服务器
            SFTPUtil sftpUtil = new SFTPUtil();
            sftpUtil.login();
            sftpUtil.upload("/upload/cmmdty",newName,param.getInputStream());
            sftpUtil.logout();
            result.setData("/upload/cmmdty/"+newName);
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    @Override
    public Result<String> removeImg(String url) {
        Result<String> result = new Result<>();

        try {
            String dict = url.substring(0,url.lastIndexOf("/")+1);
            String name = url.substring(url.lastIndexOf("/")-1);
            SFTPUtil sftpUtil = new SFTPUtil();
            sftpUtil.login();
            sftpUtil.delete(dict,name);
            sftpUtil.logout();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public Result<String> addCommodity(Map param) {
        Result<String> result = new Result<>();
        Commodity commodity = new Commodity();
        commodity.setCommodityName((String) param.get("cmmdtyName"));
        commodity.setCommodityDesc((String) param.get("cmmdtyDesc"));
        commodity.setPlatformCode((String) param.get("platformCode"));
        commodity.setActivityCode((String) param.get("activityCode"));
        commodity.setUrl((String) param.get("url"));
        commodity.setPrice(new BigDecimal((String) param.get("cmmdtyPrice")));
        commodityMapper.insert(commodity);
        return result;
    }

    @Override
    public void readImg(HttpServletRequest request, HttpServletResponse response) {
        SFTPUtil sftpUtil = new SFTPUtil();
        try {
            String path = request.getParameter("url");
            sftpUtil.login();
            InputStream is = sftpUtil.getStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStream os = response.getOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())){
                baos.write(ch);
            }
            os.write(baos.toByteArray());
            is.close();
            baos.close();
            os.close();
            sftpUtil.logout();
        } catch (IOException | SftpException e) {
            e.printStackTrace();
            sftpUtil.logout();
        }
    }

}

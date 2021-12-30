package com.more.forhelp.controller.login;

import com.more.forhelp.init.login.UserLoginService;
import com.more.forhelp.until.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserLoginService userLoginService;

    @RequestMapping("/userLogin")
    public Result<String> userLogin(@RequestBody String param ){

        Result<String> result = userLoginService.userLogin(param);

        return result;
    }

    @RequestMapping("/userRegister")
    public Result<String> userRegister(@RequestBody String param ){

        Result<String> result = userLoginService.userRegister(param);
        return result;
    }

    @RequestMapping("/checkUserName")
    public Result<String> checkUserName(@RequestBody String param ){

        Result<String> result = userLoginService.checkUserName(param);
        return result;
    }
}

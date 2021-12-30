package com.more.forhelp.init.login;

import com.more.forhelp.until.Result;

public interface UserLoginService {
    Result<String> userRegister(String param);

    Result<String> checkUserName(String param);

    Result<String> userLogin(String param);
}

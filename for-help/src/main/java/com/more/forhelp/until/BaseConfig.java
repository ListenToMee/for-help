package com.more.forhelp.until;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BaseConfig {
    @Value("${sftp.username}")
    private String username;
    private static String name;
    /** FTP 登录密码*/
    @Value("${sftp.password}")
    private String password;
    private static String word;
    /** FTP 服务器地址IP地址*/
    @Value("${sftp.host}")
    private String host;
    private static String host1;
    /** FTP 端口*/
    @Value("${sftp.port}")
    private int port;
    private static int port1;

    public BaseConfig(){
        System.out.println(">>>>>>>>>>>>>>>>>>   sftp配置已加载");
    }
    @PostConstruct
    public void getApiToken() {
        name = this.username;
        word = this.password;
        host1 = this.host;
        port1 = this.port;
    }


    public static String getUsername() {
        return name;
    }

    public static String getPassword() {
        return word;
    }

    public static String getHost() {
        return host1;
    }

    public static int getPort() {
        return port1;
    }


}

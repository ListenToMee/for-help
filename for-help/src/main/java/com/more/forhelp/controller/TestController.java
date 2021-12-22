package com.more.forhelp.controller;

import com.more.forhelp.entity.User;
import com.more.forhelp.mapper.UserMapper;
import com.more.forhelp.until.SnowFlakeUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/test")
    public String test(){
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//        userList.forEach(System.out::println);

//        SnowFlakeUntil worker = new SnowFlakeUntil( );
//        for (int i = 0; i < 22; i++) {
//            System.out.println(worker.nextId());
//        }
        return "sucess";
    }
}

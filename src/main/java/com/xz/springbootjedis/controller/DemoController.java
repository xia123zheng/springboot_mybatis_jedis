package com.xz.springbootjedis.controller;
import java.util.ArrayList;
import java.util.List;

import com.xz.springbootjedis.model.ResponseModal;
import com.xz.springbootjedis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;
import com.xz.springbootjedis.entity.User;
import com.xz.springbootjedis.model.ResponseModal;
import com.xz.springbootjedis.service.RedisService;
import com.xz.springbootjedis.service.UserService;

@RestController
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/users")
    public ResponseModal users(){
        List<User> users = userService.getAll();
        ResponseModal modal = new ResponseModal(200,true,"",users);
        return modal;
    }

    @RequestMapping("/redis/set")
    public ResponseModal redisSet(@RequestParam("value")String value){
        redisService.set("name", value);
        return new ResponseModal(200, true, "success", null);
    }

    @RequestMapping("/redis/get")
    public ResponseModal redisGet(){
        String name = redisService.get("name");
        return new ResponseModal(200, true,"success",name);
    }

    //获取数据库数据
    @RequestMapping("/mysql/all")
    public ResponseModal mysqlAllUsers(){
        List<User>  list = new ArrayList<>();
        list = userService.getAll();
        return new ResponseModal(200, true,"success",list);
    }

}
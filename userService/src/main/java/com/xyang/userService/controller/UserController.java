package com.xyang.userService.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xyang.userService.entity.User;
import com.xyang.userService.service.UserService;
import org.springframework.web.bind.annotation.*;

import org.json.JSONObject;
import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Inject
    UserService userService;
    //login
    @RequestMapping(value = "/login" ,method= RequestMethod.POST)
    public String login(@RequestBody String str){
        JSONObject json = new JSONObject(str);
        String name = json.getString("name");
        String password = json.getString("password");
        if(userService.login(name,password))
            return "login succcessfully";
       return "Denied";
    }
    //add
    //requestBody 对json进行反序列化
    @RequestMapping(value = "/add" ,method= RequestMethod.POST)
    public void add(@RequestBody User user){
        userService.add(user);
    }
    //delete
    @RequestMapping(value = "/delete" ,method= RequestMethod.GET)
    public void delete(){

    }
    //modify
    @RequestMapping(value = "/modify" ,method= RequestMethod.GET)
    public void modify(){

    }
    //query
    @RequestMapping(value = "/query" ,method= RequestMethod.GET)
    public void query(){

    }
}

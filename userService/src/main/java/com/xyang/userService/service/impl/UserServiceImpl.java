package com.xyang.userService.service.impl;

import com.xyang.userService.dao.UseDAO;
import com.xyang.userService.entity.User;
import com.xyang.userService.service.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    UseDAO UserServiceDAO;
    public boolean login(String name,String password){
        return UserServiceDAO.login(name,password);
    }

    public void add(User user){
        UserServiceDAO.add(user);
    }
}

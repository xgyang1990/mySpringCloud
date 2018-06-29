package com.xyang.userService.service;

import com.xyang.userService.entity.User;

public interface UserService {
    boolean login(String name,String password);

    void add(User user);
}

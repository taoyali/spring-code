package com.tyl.service;

import com.tyl.pojo.User;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);
}
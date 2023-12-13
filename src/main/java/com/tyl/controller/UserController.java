package com.tyl.controller;

import com.tyl.pojo.Result;
import com.tyl.pojo.User;
import com.tyl.service.UserService;
import com.tyl.utils.JwtUtil;
import com.tyl.utils.Md5Util;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findByUserName(username);
        if (user == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findByUserName(username);
        if (user == null) {
            return Result.error("用户不存在");
        }

        if (!Md5Util.getMD5String(password).equals(user.getPassword())) {
            return Result.error("用户名或密码错误");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("userId", user.getId());
        String token = JwtUtil.genToken(claims);
        return Result.success(token);
    }
}

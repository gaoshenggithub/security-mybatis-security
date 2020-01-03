package com.security.securitymybatissecurity.controller;

import com.security.securitymybatissecurity.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/logins")
    public UserVO login(@RequestBody UserVO userVO) {
        return null;
    }
}

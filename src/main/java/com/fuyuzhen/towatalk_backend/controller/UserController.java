package com.fuyuzhen.towatalk_backend.controller;

import com.fuyuzhen.towatalk_backend.common.ServerResponse;
import com.fuyuzhen.towatalk_backend.entity.User;
import com.fuyuzhen.towatalk_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    UserService userServiceImpl;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    ServerResponse userRegister(@RequestBody User user){
        return userServiceImpl.userRegister(user);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    ServerResponse userLogin(@RequestBody User user){
        return userServiceImpl.userLogin(user);
    }
}

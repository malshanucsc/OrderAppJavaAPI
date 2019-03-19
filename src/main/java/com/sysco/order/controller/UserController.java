package com.sysco.order.controller;

import com.sysco.order.model.TempUser;
import com.sysco.order.model.User;
import com.sysco.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //logging functionality
    @PostMapping(value="/user/login")
    public ResponseEntity logIn(HttpServletResponse httpServletResponse, @RequestBody TempUser LogInUser){
        return userService.logIn(httpServletResponse,LogInUser);
    }

    //register
    @PostMapping("user/register")
    public ResponseEntity createUser(@RequestBody User registerUser){
        return userService.createUser(registerUser);
    }
    //updating the user
//    @PutMapping(value="/user", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public String update(@RequestBody String jsonInputSring){
//
//        return userService.updateUser(jsonInputSring);
//
//    }
}
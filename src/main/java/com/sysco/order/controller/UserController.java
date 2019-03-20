package com.sysco.order.controller;

import com.sysco.order.model.TempUser;
import com.sysco.order.model.User;
import com.sysco.order.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "User Rest End Points", description = "Shows the user info")
public class UserController {

    @Autowired
    private UserService userService;

    //logging functionality
    @ApiOperation(value="Returns user data when credentials are valid")
    @PostMapping(value="/users/login")
    public ResponseEntity logIn(HttpServletResponse httpServletResponse, @RequestBody TempUser LogInUser){
        return userService.logIn(httpServletResponse,LogInUser);
    }

    //register
    @ApiOperation(value="Returns Success when registering user")
    @PostMapping("users/register")
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
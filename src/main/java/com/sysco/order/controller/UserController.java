package com.sysco.order.controller;

import Order_package.Authetication.CookieUtil;
import Order_package.Authetication.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysco.order.model.TempUser;
import com.sysco.order.model.User;
import com.sysco.order.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;
    //logging functionality
    @PostMapping(value="/user/login")
    public ResponseEntity logIn(HttpServletResponse httpServletResponse, @RequestBody TempUser LogInUser){

        //define security key for jwt
        String signingKey = "signingKey";
        String jwtTokenCookieName = "JWT-TOKEN";
        User availableUser = repository.findByUserName(LogInUser.getUserName());

        if(availableUser !=null && availableUser.matchPassword(LogInUser.getpassword())){
            String token = JwtUtil.generateToken(signingKey, availableUser.getUserName());
            CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
            return ResponseEntity.status(200).body(availableUser);
        }else {
            return ResponseEntity.status(404).body(LogInUser);
        }
    }

    //register
    @PostMapping("user/register")
    public ResponseEntity createOrder(@RequestBody User registerUser){

        //checking the availablity of user name

        User availableUser = repository.findByUserName(registerUser.getUserName());

        if(availableUser!=null){
            return ResponseEntity.status(404).body(registerUser);

        }else{
            repository.save(registerUser);
            return ResponseEntity.status(200).body(registerUser);
        }
    }

    //updating the user
    @PutMapping(value="/user", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String update(@RequestBody String jsonInputSring){

        JSONObject jsonInputObject = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        User updatingUser = new User();

        try {
            jsonInputObject = new JSONObject(jsonInputSring);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonResponseObject = new JSONObject();

        try {
            updatingUser = objectMapper.readValue( jsonInputObject.get("user").toString(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }




        try {
            if(repository.exists(updatingUser.getId())){

                System.out.println(updatingUser.getpassword());

                if(updatingUser.matchPassword(jsonInputObject.get("currentPassword").toString())){
                    System.out.println(updatingUser.getId());


                    //updatingUser.setpassword(jsonInputObject.get("newPassword").toString());

                    repository.save(updatingUser);
                    jsonResponseObject = new JSONObject("{'message':'user_updated_successfully'}");

                }


            }else {

                try {
                    jsonResponseObject = new JSONObject("{'message':'user_does_not_exist'}");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonResponseObject.toString();


    }


}

package com.sysco.order.service;

import com.sysco.order.dao.UserDetailDao;
import com.sysco.order.exception.EmptyUserException;
import com.sysco.order.exception.UserCreationException;
import com.sysco.order.model.TempUser;
import com.sysco.order.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Autowired
    private UserDetailDao userDetailDao;

    public ResponseEntity logIn(HttpServletResponse httpServletResponse,TempUser LogInUser) {
        String signingKey = "signingKey";
        String jwtTokenCookieName = "JWT-TOKEN";
        try{
            User availableUser = userDetailDao.findByUserName(LogInUser.getUserName());
            if (availableUser != null && availableUser.matchPassword(LogInUser.getpassword())) {
//            String token = JwtUtil.generateToken(signingKey, availableUser.getUserName());
//            CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
              return ResponseEntity.status(200).body(availableUser);
            }else{
                return ResponseEntity.status(401).body("invalid credentials");
            }
        }catch(EmptyUserException eue){
            return ResponseEntity.status(404).body(eue.getMessage());
        }
    }

    public ResponseEntity createUser(User registerUser){
        try{
            User availableUser = userDetailDao.findByUserName(registerUser.getUserName());
            if(availableUser==null) {
                return ResponseEntity.status(200).body(userDetailDao.createUser(registerUser.getUserName(), registerUser.getpassword()));
            }else{
                return ResponseEntity.status(403).body("user already exists");
            }
        }catch (UserCreationException uce){
            return ResponseEntity.status(500).body(uce.getMessage());
        }
    }


//    public String updateUser(String jsonInputSring){
//
//        JSONObject jsonInputObject = new JSONObject();
//        ObjectMapper objectMapper = new ObjectMapper();
//        User updatingUser = new User();
//
//        try {
//            jsonInputObject = new JSONObject(jsonInputSring);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JSONObject jsonResponseObject = new JSONObject();
//
//        try {
//            updatingUser = objectMapper.readValue( jsonInputObject.get("user").toString(), User.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//        try {
//            if(userDetailDao.userExists(updatingUser.getId())!=null){
//
//                System.out.println(updatingUser.getpassword());
//
//                if(updatingUser.matchPassword(jsonInputObject.get("currentPassword").toString())){
//                    System.out.println(updatingUser.getId());
//
//
//                    //updatingUser.setpassword(jsonInputObject.get("newPassword").toString());
//
//                    userDetailDao.updateOrder(updatingUser, updatingUser.getId());
//                    jsonResponseObject = new JSONObject("{'message':'user_updated_successfully'}");
//
//                }
//
//
//            }else {
//
//                try {
//                    jsonResponseObject = new JSONObject("{'message':'user_does_not_exist'}");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return jsonResponseObject.toString();
//
//
//    }
}

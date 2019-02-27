package Order_package.Controllers;

import Order_package.Interfaces.UserRepository;
import Order_package.Models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;


    @PostMapping(value="user/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String logIn(@RequestBody String jsonInputString){

        //defining reqest and response json objects

        JSONObject jsonInputObject = new JSONObject();
        JSONObject jsonResponseObject = new JSONObject();
        User availableUser = new User();
        boolean validUser = false;

        try {
            jsonInputObject = new JSONObject(jsonInputString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            availableUser = repository.findByUserName(jsonInputObject.get("userName").toString());


            if(availableUser.matchPassword(jsonInputObject.get("password").toString())){

                validUser=true;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(validUser) {

            try {
                jsonResponseObject.put("user",availableUser);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else {

            try {
                jsonResponseObject = new JSONObject("{'message':'invalid_credentials','userName':'"+jsonInputObject.get("userName").toString()+"'}");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return jsonResponseObject.toString();

    }



    @PostMapping(value="user/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String createOrder(@RequestBody String jsonInputString){

        //defining reqest and response json objects
        JSONObject jsonInputObject = new JSONObject();
        JSONObject jsonResponseObject = new JSONObject();


        //checking the availablity of user name

        //obtaining input string userName

        String userName ="";
        User newUser = new User();

        try {
            jsonInputObject = new JSONObject(jsonInputString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            userName = jsonInputObject.get("userName").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //obtaining available userName
        User availableUser =  repository.findByUserName(userName);

        if(availableUser!=null){

            try {
                jsonResponseObject = new JSONObject("{'message':'user_already_exist'}");
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }else{

            //creating new User

            ObjectMapper mapper = new ObjectMapper();
            newUser = new User();



            try {
                newUser = mapper.readValue(jsonInputString, User.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                jsonResponseObject.put("user",newUser.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //repository.save(newUser);


        }

        return(jsonResponseObject.toString());



    }


}

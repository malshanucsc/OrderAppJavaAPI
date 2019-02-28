package Order_package.Controllers;

import Order_package.Models.Item;
import Order_package.Models.OrderData;
import Order_package.Interfaces.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController{



    @Autowired
    private OrderRepository repository;


    //retreiving all orders
    @GetMapping("/orders")
    public List<OrderData> index(){

        List<OrderData> orderslist = repository.findAll();
        return (orderslist);
    }

    //retreiving all orders by order name
    @GetMapping("/orders/getbyordername")
    @ResponseBody
    public List<OrderData> findByOrderName(@RequestParam String OrderName){

        List<OrderData> orderListByOrderName = repository.findByOrderName(OrderName);
        return (orderListByOrderName);
    }

    //retreiving all orders by customer name
    @GetMapping("/orders/getbycustomername")
    @ResponseBody
    public List<OrderData> findByCustomerName(@RequestParam String CustomerName){

        List<OrderData> orderListByCustomerName = repository.findByCustomerName(CustomerName);
        return (orderListByCustomerName);
    }

    //create order
    @PostMapping(value="orders/create")
    public void createOrder(@RequestBody String jsonString){
        JSONObject jsonObject = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();



        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OrderData newOrder;
        try {
            String orderName = jsonObject.get("orderName").toString();
            String customerName = jsonObject.get("customerName").toString();
            double grandTotal = Double.parseDouble(jsonObject.get("grandTotal").toString());
            String address = jsonObject.get("address").toString();


            ArrayList<Item> itemList = new Gson().fromJson(jsonObject.get("itemsList").toString(), new TypeToken<ArrayList<Item>>(){}.getType());

            newOrder = new OrderData(orderName, customerName, grandTotal, address, itemList);
            repository.save(newOrder);


        } catch (JSONException e) {
            e.printStackTrace();
        }




    }


    @DeleteMapping(value="/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String delete(@RequestParam String orderId){

        JSONObject jsonResponseObject = new JSONObject();

        if(repository.exists(orderId)){
            repository.delete(orderId);

            if(repository.exists(orderId)){
                try {
                    jsonResponseObject = new JSONObject("{'message':'order_deleted_successfully'}");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else {
                try {
                    jsonResponseObject = new JSONObject("{'message':'order_deletion_failed'}");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }else {

            try {
                jsonResponseObject = new JSONObject("{'message':'order_does_not_exist'}");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return jsonResponseObject.toString();


    }

    //updating the object
    @PutMapping(value="/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String update(@RequestBody String jsonInputSring){

        JSONObject jsonInputObject = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        OrderData updatingOrder = new OrderData();



        try {
            jsonInputObject = new JSONObject(jsonInputSring);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonResponseObject = new JSONObject();

        try {
            if(repository.exists(jsonInputObject.get("id").toString())){
                try {
                    updatingOrder = objectMapper.readValue(jsonInputSring, OrderData.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(repository.save(updatingOrder));




            }else {

                try {
                    jsonResponseObject = new JSONObject("{'message':'order_does_not_exist'}");
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

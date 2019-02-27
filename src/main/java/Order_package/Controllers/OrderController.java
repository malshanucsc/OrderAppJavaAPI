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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController{



    @Autowired
    private OrderRepository repository;

    @GetMapping("/orders")
    public List<OrderData> index(){

        List<OrderData> orderslist = repository.findAll();



        return (orderslist);
    }

    @GetMapping("/orders/getbyname")
    @ResponseBody
    public OrderData findByName(@RequestParam String orderName){
        System.out.println(orderName);

        OrderData orderbyname = repository.findByOrderName(orderName);
        System.out.print(orderbyname);

        return (orderbyname);
    }

    @GetMapping("/orders/customer/{customername}")
    public List<OrderData> findByCustomerName(@PathVariable String customername){

        List<OrderData> orderbyname = repository.findByCustomerName(customername);

        return (orderbyname);
    }

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
//            Map<List<Item>,Object> itemList= objectMapper.convertValue(jsonObject.get("itemsList"),Map.class);
//            List<Item> itemList= jsonObject.get("itemList");

            ArrayList<Item> itemList = new Gson().fromJson(jsonObject.get("itemsList").toString(), new TypeToken<ArrayList<Item>>(){}.getType());


            newOrder = new OrderData(orderName, customerName, grandTotal, address, itemList);
            repository.save(newOrder);


        } catch (JSONException e) {
            e.printStackTrace();
        }




    }




//    @GetMapping("/order")
//    public List<Order> index(){
//        return orderMockedData.fetchBlogs();
//    }

/*
    @GetMapping("/order/{id}")
    public Order show(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return orderMockedData.getBlogById(blogId);
    }


    @PostMapping("/order/search")
    public List<Order> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return orderMockedData.searchBlogs(searchTerm);
    }

    @PostMapping("/order")
    public Order create(@RequestBody Map<String, String> body){
        int id = Integer.parseInt(body.get("id"));
        String title = body.get("title");
        String content = body.get("content");
        return orderMockedData.createBlog(id, title, content);
    }

    @PutMapping("/order/{id}")
    public Order update(@PathVariable String id, @RequestBody Map<String, String> body){
        int blogId = Integer.parseInt(id);
        String title = body.get("title");
        String content = body.get("content");
        return orderMockedData.updateBlog(blogId, title, content);
    }

    @DeleteMapping("order/{id}")
    public boolean delete(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return orderMockedData.delete(blogId);
    }


*/

}

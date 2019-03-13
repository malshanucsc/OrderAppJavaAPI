package com.sysco.order.controller;

import com.sysco.order.repository.OrderRepository;
import com.sysco.order.model.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController{

    @Autowired
    private OrderRepository repository;


    //retreiving all orders
    @GetMapping("/orders")
    public ResponseEntity orders(){
        //need to
        List<OrderData> allOrders = repository.findAll();
        if(allOrders!=null) {

            return ResponseEntity.status(200).body(allOrders);

        }else{
            return  ResponseEntity.status(404).body("no_orders_found");

        }

    }

    //retrieve single order
    @GetMapping("/order/{id}")
    public ResponseEntity order(@PathVariable String id){

        OrderData singleOrder = repository.findById(id);
        if(singleOrder!=null){
            return ResponseEntity.status(200).body(singleOrder);
        }else{
            return  ResponseEntity.status(404).body("order_not_found");
        }

    }

    //create order
    @PostMapping("/order")
    public ResponseEntity createOrder(@RequestBody OrderData newOrder){


        return ResponseEntity.status(201).body(repository.save(newOrder));

    }

    //deleting an order
    @DeleteMapping(value="/order/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity delete(@PathVariable String id){



        if(repository.findById(id)!=null){
            repository.delete(id);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    //updating the order object
    @PutMapping(value="/order", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@RequestBody OrderData currentOrder){

        if (repository.findById(currentOrder.getId())!=null){
            repository.save(currentOrder);
            return ResponseEntity.status(200).body(currentOrder);

        }else{
            return ResponseEntity.status(404).build();
        }

    }


}

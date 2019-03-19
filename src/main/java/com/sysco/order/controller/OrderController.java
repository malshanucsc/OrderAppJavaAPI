package com.sysco.order.controller;

import com.sysco.order.model.OrderData;
import com.sysco.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController{

    @Autowired
    private OrderService orderService;

    //retreiving all orders
    @GetMapping("/orders")
    public ResponseEntity orders(){
        return orderService.getAllOrders();
    }

    //retrieve single order
    @GetMapping("/orders/{id}")
    public ResponseEntity order(@PathVariable String id){
        return orderService.getSingleOrder(id);
    }

    //create order
    @PostMapping("/orders")
    public ResponseEntity createOrder(@RequestBody OrderData newOrder){
        return orderService.createOrder(newOrder);
    }

    //deleting an order
    @DeleteMapping(value="/orders/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity delete(@PathVariable String id){
        return orderService.deleteOrder(id);
    }

    //updating the order object
    @PutMapping(value="/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@RequestBody OrderData currentOrder){
        return orderService.updateOrder(currentOrder);
    }
}
package com.sysco.order.controller;

import com.sysco.order.model.OrderData;
import com.sysco.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * CRUD operations on orders
 * @author mwan5534 on 3/21/19
 *
 */
@RestController
@Api(value = "Order Rest End Points", description = "Shows the order info")
public class OrderController{

    @Autowired
    private OrderService orderService;

    /**
     * retreiving all orders
     * @return ResponseEntity OrderData array
     */
    @ApiOperation(value="Returns all orders")
    @GetMapping("/orders")
    public ResponseEntity orders(){return orderService.getAllOrders(); }

    /**
     * retrieve single order
     * @param id
     * @return ResponseEntity OrderData
     */
    @ApiOperation(value="Returns an order when id given")
    @GetMapping("/orders/{id}")
    public ResponseEntity order(@PathVariable String id){
        return orderService.getSingleOrder(id);
    }

    /**
     * create order
     * @param newOrder
     * @return ResponseEntity Message
     */
    @ApiOperation(value="Create an Order when the json order object passed")
    @PostMapping("/orders")
    public ResponseEntity createOrder(@RequestBody OrderData newOrder){
        return orderService.createOrder(newOrder);
    }

    /**
     * deleting an order
     * @param id
     * @return ResponseEntity Message
     */
    @ApiOperation(value="Delete an order when id given")
    @DeleteMapping(value="/orders/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity delete(@PathVariable String id){
        return orderService.deleteOrder(id);
    }

    /**
     * updating the order object
     * @param currentOrder
     * @return ResponseEntity OrderData
     */
    @ApiOperation(value="Update the order when order sent as json")
    @PutMapping(value="/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@RequestBody OrderData currentOrder){
        return orderService.updateOrder(currentOrder);
    }
}
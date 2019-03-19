package com.sysco.order.service;

import com.sysco.order.dao.OrderDetailDao;
import com.sysco.order.exception.EmptyOrderException;
import com.sysco.order.exception.OrderCreationException;
import com.sysco.order.model.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDetailDao  orderDetailDao;

    public ResponseEntity getAllOrders(){
        try{
            List<OrderData> allOrders = orderDetailDao.getAllOrders();
            return ResponseEntity.status(200).body(allOrders);
        }catch (EmptyOrderException eoe){
            return  ResponseEntity.status(500).body(eoe.getMessage());
        }
    }

    public ResponseEntity getSingleOrder(String id){
        try{
            OrderData singleOrder = orderDetailDao.getSingleOrder(id);
            return ResponseEntity.status(200).body(singleOrder);
        }catch(EmptyOrderException eoe) {
            return ResponseEntity.status(404).body(eoe.getMessage());
        }
    }

    public ResponseEntity createOrder(OrderData newOrder){
        try{
            orderDetailDao.createOrder(newOrder);
            return ResponseEntity.status(201).body("success");
        }catch (OrderCreationException oce){
            return  ResponseEntity.status(500).body("order_not_saved");
        }
    }

    public ResponseEntity deleteOrder(String id){
        try{
            orderDetailDao.deleteOrder(id);
            return ResponseEntity.status(201).body("order_deleted_successfully");
        }catch (EmptyOrderException eoe){
            return  ResponseEntity.status(500).body(eoe.getMessage());
        }
    }

    public ResponseEntity updateOrder(OrderData currentOrder){
        try{
            orderDetailDao.updateOrder( currentOrder,currentOrder.getId());
            return ResponseEntity.status(200).body(currentOrder);
        }catch (OrderCreationException oce){
            return  ResponseEntity.status(500).body(oce.getMessage());
        }
    }
}
//package com.sysco.order.controller;
//
//import com.sysco.order.model.OrderData;
//import com.sysco.order.repository.OrderRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//public class OrderControllerTest {
//
//    //OrderController orderControllerObject = new OrderController();
//
//    @Mock
//    OrderRepository repository;
//
//    @InjectMocks
//    OrderController orderController;
//
//    @Test
//    public void testOrders(){
//
//        List<OrderData> orderList = new ArrayList<>();
//        when(repository.findAll()).thenReturn(orderList);
//        ResponseEntity returnedResponseEntity = orderController.orders();
//
//        assertEquals(ResponseEntity.status(200).body(orderList),returnedResponseEntity);
//        assertNotNull(returnedResponseEntity);
//
//    }
//
//    @Test
//    public void testOrder(){
//
//        OrderData responseOrder = new OrderData();
//        when(repository.findById("5c8648b87753a06da25a6ee9")).thenReturn(responseOrder);
//
//        ResponseEntity returnedResponseEntity = orderController.order("5c8648b87753a06da25a6ee9");
//        assertEquals(ResponseEntity.status(200).body(responseOrder),returnedResponseEntity);
//        assertNotNull(returnedResponseEntity);
//
//    }
//
//    @Test
//    public void testCreateOrder(){
//
//        OrderData newOrder = new OrderData();
//        when(repository.save(newOrder)).thenReturn(newOrder);
//
//        ResponseEntity returnedResponseEntity = orderController.createOrder(newOrder);
//        assertEquals(ResponseEntity.status(201).body(newOrder),returnedResponseEntity);
//        assertNotNull(returnedResponseEntity);
//
//    }
//
//    @Test
//    public void testUpdate(){
//
//        OrderData newOrder = new OrderData();
//        when(repository.save(newOrder)).thenReturn(newOrder);
//
//        ResponseEntity returnedResponseEntity = orderController.createOrder(newOrder);
//        assertEquals(ResponseEntity.status(201).body(newOrder),returnedResponseEntity);
//        assertNotNull(returnedResponseEntity);
//
//
//
//    }
//
//
//}
//

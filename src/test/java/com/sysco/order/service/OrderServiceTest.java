package com.sysco.order.service;

import com.sysco.order.dao.OrderDetailDao;
import com.sysco.order.exception.EmptyOrderException;
import com.sysco.order.exception.OrderCreationException;
import com.sysco.order.model.Item;
import com.sysco.order.model.OrderData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@PrepareForTest({OrderService.class})
public class OrderServiceTest {

    private String orderListString = "[{'id': 8,'orderName': 'Order 1','customerName': 'Malshan','grandTotal': 0,'address':'Dehiwala, CMB.','itemsList': null},{'id': 9,'orderName': 'Order 2','customerName': 'Malshan Wanigasekara','grandTotal': 0,'address': 'Slave Islands, CMB','itemsList': null}]";
    private OrderData order_1 = new OrderData();
    private OrderData order_2 = new OrderData();
    private OrderData singleOrder = new OrderData();
    private OrderData createOrder = new OrderData();
    private Item item1 = new Item();
    private Item item2 = new Item();
    private String single_order_id ="1";
    private List<Item> itemList = new ArrayList<>();
    private List<OrderData> orderListWithData = new ArrayList<>();
    private List<OrderData> emptyOrderList = new ArrayList<>();


    @Mock
    OrderDetailDao orderDetailDao;

    @InjectMocks
    OrderService orderService;

    @Before
    public void setUp(){



        //setting order 1
        order_1.setId(1);
        order_1.setOrderName("Order 1");
        order_1.setCustomerName("Malshan");
        order_1.setAddress("Dehiwala");

        order_2.setId(2);
        order_2.setOrderName("Order 2");
        order_2.setCustomerName("Wanigasekara");
        order_2.setAddress("CMB");

        orderListWithData.add(order_1);
        orderListWithData.add(order_2);



        item1.setId(1);
        item1.setItemName("Cabbage");
        item1.setQuantity(2);
        item1.setUnit("Kg");
        item1.setUnitValue(2);

        item2.setId(1);
        item2.setItemName("Cabbage");
        item2.setQuantity(2);
        item2.setUnit("Kg");
        item2.setUnitValue(2);

        itemList.add(item1);
        itemList.add(item2);


        singleOrder.setId(Integer.parseInt(single_order_id));
        singleOrder.setItemsList(itemList);


    }

    @Test
    public void getAllOrders_Should_ReturnResponseEntityWithOrderList(){

        when(orderDetailDao.getAllOrders()).thenReturn(orderListWithData);
        ResponseEntity orderListResponse = orderService.getAllOrders();
        Assert.assertNotNull(orderListResponse);
        Assert.assertEquals(orderListWithData, orderListResponse.getBody());
        Assert.assertEquals(200,orderListResponse.getStatusCodeValue());
    }


    @Test
    public void getAllOrders_Should_ReturnResponseEntityWithEmptyList(){

        when(orderDetailDao.getAllOrders()).thenReturn(emptyOrderList);
        ResponseEntity orderListResponse = orderService.getAllOrders();
        Assert.assertNotNull(orderListResponse);
        Assert.assertEquals(emptyOrderList, orderListResponse.getBody());
        Assert.assertEquals(200,orderListResponse.getStatusCodeValue());
    }

    @Test
    public void getAllOrders_Should_ReturnResponseEntityWithExceptionMessage() {


        when(orderDetailDao.getAllOrders()).thenThrow(EmptyOrderException.class);
        ResponseEntity orderListResponse = orderService.getAllOrders();
        Assert.assertNotNull(orderListResponse);
        Assert.assertNull(orderListResponse.getBody());
        Assert.assertEquals(500, orderListResponse.getStatusCodeValue());
    }


    @Test
    public void getSingleOrder_Should_ReturnResponseEntityWithAnOrderItemDetails_When_CorrectIdGiven(){

        when(orderDetailDao.getSingleOrder(single_order_id)).thenReturn(singleOrder);
        ResponseEntity orderResponse = orderService.getSingleOrder(single_order_id);
        Assert.assertNotNull(orderResponse);
        Assert.assertEquals(singleOrder, orderResponse.getBody());
        Assert.assertEquals(200, orderResponse.getStatusCodeValue());


    }

    @Test
    public void getSingleOrder_Should_ReturnResponseEntityWithErrorCode_When_IncorrectIdGiven(){

        when(orderDetailDao.getSingleOrder("5")).thenThrow(EmptyOrderException.class);
        ResponseEntity orderResponse = orderService.getSingleOrder("5");
        Assert.assertNotNull(orderResponse);
        Assert.assertNull(orderResponse.getBody());
        Assert.assertEquals(404, orderResponse.getStatusCodeValue());


    }


    @Test
    public void createOrder_Should_ReturnResponseEntityWithSuccessMessage(){
        when(orderDetailDao.createOrder(new OrderData())).thenReturn(true);
        ResponseEntity createOrderResponse = orderService.createOrder(new OrderData());
        Assert.assertNotNull(createOrderResponse);
        Assert.assertEquals("success", createOrderResponse.getBody());
        Assert.assertEquals(201, createOrderResponse.getStatusCodeValue());
    }

    @Test
    public void createOrder_Should_ReturnResponseEntityWithErrorCode(){
        when(orderDetailDao.createOrder(createOrder)).thenThrow(OrderCreationException.class);
        ResponseEntity createOrderResponse = orderService.createOrder(createOrder);
        Assert.assertNotNull(createOrderResponse);
        Assert.assertEquals("order_not_saved", createOrderResponse.getBody());
        Assert.assertEquals(500, createOrderResponse.getStatusCodeValue());
    }

    @Test
    public void deleteOrder_Should_ReturnResponseEntityWithSuccessMessage(){
        when(orderDetailDao.deleteOrder(single_order_id)).thenReturn(true);
        ResponseEntity deleteOrderResponse = orderService.deleteOrder(single_order_id);
        Assert.assertNotNull(deleteOrderResponse);
        Assert.assertEquals("order_deleted_successfully", deleteOrderResponse.getBody());
        Assert.assertEquals(201, deleteOrderResponse.getStatusCodeValue());
    }

    @Test
    public void deleteOrder_Should_ReturnResponseEntityWithErrorCode(){
        when(orderDetailDao.deleteOrder("5")).thenThrow(EmptyOrderException.class);
        ResponseEntity deleteOrderResponse = orderService.deleteOrder("5");
        Assert.assertNotNull(deleteOrderResponse);
        Assert.assertNull(deleteOrderResponse.getBody());
        Assert.assertEquals(500, deleteOrderResponse.getStatusCodeValue());
    }

    @Test
    public void updateOrder_Should_ReturnResponseEntityWithOrderData(){
        when(orderDetailDao.updateOrder(singleOrder,Integer.parseInt(single_order_id))).thenReturn(singleOrder);
        ResponseEntity updateOrderResponse = orderService.updateOrder(singleOrder);
        Assert.assertNotNull(updateOrderResponse);
        Assert.assertEquals(singleOrder, updateOrderResponse.getBody());
        Assert.assertEquals(200, updateOrderResponse.getStatusCodeValue());
    }

    @Test
    public void updateOrder_Should_ReturnResponseEntityWithErrorCode(){
        when(orderDetailDao.updateOrder(singleOrder,Integer.parseInt(single_order_id))).thenThrow(OrderCreationException.class);
        ResponseEntity updateOrderResponse = orderService.updateOrder(singleOrder);
        Assert.assertNotNull(updateOrderResponse);
        Assert.assertNull(updateOrderResponse.getBody());
        Assert.assertEquals(500, updateOrderResponse.getStatusCodeValue());
    }

}

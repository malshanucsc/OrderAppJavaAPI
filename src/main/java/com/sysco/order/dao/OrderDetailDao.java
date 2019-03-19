package com.sysco.order.dao;

import com.sysco.order.model.OrderData;

import java.util.List;

public interface OrderDetailDao {

    List<OrderData> getAllOrders();

    OrderData getSingleOrder(String id);

    boolean createOrder(OrderData newOrder);

    boolean deleteOrder(String id);

    OrderData updateOrder(OrderData currentOrder, int id);

}

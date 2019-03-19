package com.sysco.order.dao;

import com.sysco.order.model.Item;

import java.util.List;

public interface ItemDetailDao {
    List<Item> getAllItems();

    Item getSingleItem(String id);

//    void createOrder(OrderData newOrder);
//
//    void deleteOrder(String id);
//
//    OrderData updateOrder(OrderData currentOrder, String id);
}

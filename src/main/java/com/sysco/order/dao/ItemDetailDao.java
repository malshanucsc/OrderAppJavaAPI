package com.sysco.order.dao;

import com.sysco.order.model.Item;
import java.util.List;

/**
 * Data Access Object for Item entity
 *
 * @author mwan5534 on 3/21/19
 */
public interface ItemDetailDao {
    /**
     * retrieve all orders
     * @return List <Item>
     */
    List<Item> getAllItems();

    /**
     * retieve single order
     * @param id
     * @return Item
     */
    Item getSingleItem(String id);




//    void createOrder(OrderData newOrder);
//
//    void deleteOrder(String id);
//
//    OrderData updateOrder(OrderData currentOrder, String id);
}

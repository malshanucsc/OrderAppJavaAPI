package com.sysco.order.dao;

import com.sysco.order.model.OrderData;
import java.util.List;

/**
 * Data Access Object for OrderDara entity
 *
 * @author mwan5534 on 3/21/19
 */
public interface OrderDetailDao {

    /**
     * retrieve all orders
     * @return List <OrderData>
     */
    List<OrderData> getAllOrders();

    /**
     * retrieve single order
     * @param id
     * @return OrderData
     */
    OrderData getSingleOrder(String id);

    /**
     * create an order
     * @param newOrder
     * @return boolean
     */
    boolean createOrder(OrderData newOrder);

    /**
     * delete an order
     * @param id
     * @return boolean
     */
    boolean deleteOrder(String id);

    /**
     *
     * @param currentOrder
     * @param id
     * @return OrderData
     */
    OrderData updateOrder(OrderData currentOrder, int id);

}

package com.sysco.order.dao.impl;

import com.mysql.jdbc.Statement;
import com.sysco.order.dao.OrderDetailDao;
import com.sysco.order.exception.EmptyOrderException;
import com.sysco.order.exception.OrderCreationException;
import com.sysco.order.model.Item;
import com.sysco.order.model.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object implementation for OrderData entity
 *
 * @author mwan5534 on 3/21/19
 */
@Repository("OrderDetailDao")
public class OrderDetailDaoImpl implements OrderDetailDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<OrderData> getAllOrders() {

        String query = "SELECT * FROM order_table";
        try {
            return jdbcTemplate.query(query, new OrderDataRowMapper());
        } catch (Exception e) {
            throw new EmptyOrderException("ServerError",e);
        }
    }

    @Override
    public OrderData getSingleOrder(String id) {
        String query_for_order = "SELECT * order_table WHERE id = ? ";

        String query_for_items = "SELECT * FROM order_item INNER JOIN item ON order_item.item_id = item.id WHERE order_item.order_id = ? ";
        try {
            //OrderData orderData = jdbcTemplate.queryForObject(query_for_order,new Object[] {Integer.parseInt(id) },new OrderDataRowMapper());
            OrderData orderData = new OrderData();
            List<Item> item_set = new ArrayList<>();
            orderData.setId(Integer.parseInt(id));

            jdbcTemplate.query(query_for_items, (ResultSetExtractor<List>) resultSetObj -> {
                while (resultSetObj.next()) {
                    item_set.add(new Item(resultSetObj.getInt("item_id"), resultSetObj.getString("name"), resultSetObj.getString("unit"), resultSetObj.getDouble("transaction_unit_value"),resultSetObj.getDouble("no_units")));
                }
                return item_set;
            },id);
            orderData.setItemsList(item_set);
            return orderData;
        } catch (Exception e) {
            throw new EmptyOrderException("order not available",e);
        }

    }

    @Override
    public boolean createOrder(OrderData newOrder) {

        String order_query = "INSERT INTO order_table (order_name, customer_name, address) VALUES(?, ?, ?)";
        String order_item_query = "INSERT INTO order_item (no_units, order_id, item_id, transaction_unit_value) VALUES (?, ?, ?, ?)";
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(order_query, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, newOrder.getOrderName());
                    ps.setString(2, newOrder.getCustomerName());
                    ps.setString(3, newOrder.getAddress());
                    return ps;
                }
            }, holder);
            newOrder.setId(holder.getKey().intValue());
            List<Item> itemList = newOrder.getItemsList();

            jdbcTemplate.batchUpdate(order_item_query, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Item item = itemList.get(i);
                    ps.setDouble(1, item.getQuantity());
                    ps.setInt(2, newOrder.getId());
                    ps.setInt(3, item.getId());
                    ps.setDouble(4, item.getUnitValue());
                }

                @Override
                public int getBatchSize() {
                    return itemList.size();
                }
            });
            return true;
        } catch (Exception e) {
             throw new OrderCreationException("order save error",e);
        }
    }

    @Override
    public boolean deleteOrder(String id) {
        String delete_order_query = "DELETE FROM order_table WHERE id=?";
        try {
            jdbcTemplate.update(delete_order_query, id);
            return true;
        } catch (Exception e) {
            throw new EmptyOrderException("order cannot found or server error",e);
        }
    }

    @Override
    public OrderData updateOrder(OrderData currentOrder, int id) {
        String order_table_query = "UPDATE order_table SET order_name = ?,customer_name = ?, address = ? WHERE id = ?";
        String order_item_delete_query = "DELETE FROM order_item WHERE order_id = ?";
        String order_item_query = "INSERT INTO order_item (no_units, order_id, item_id, transaction_unit_value) VALUES (?, ?, ?, ?)";
        try {
            List<Item> itemList = currentOrder.getItemsList();
            jdbcTemplate.update(order_table_query, currentOrder.getOrderName(), currentOrder.getCustomerName(), currentOrder.getAddress(), id);
            jdbcTemplate.update(order_item_delete_query,id);

            jdbcTemplate.batchUpdate(order_item_query, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Item item = itemList.get(i);
                    ps.setDouble(1, item.getQuantity());
                    ps.setInt(2, currentOrder.getId());
                    ps.setInt(3, item.getId());
                    ps.setDouble(4, item.getUnitValue());
                }

                @Override
                public int getBatchSize() {
                    return itemList.size();
                }
            });
            return currentOrder;
        } catch (Exception e) {
            throw new OrderCreationException("order cannot be updated",e);
        }
    }

    public class OrderDataRowMapper implements RowMapper<OrderData> {
        @Override
        public OrderData mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderData orderData = new OrderData();
            orderData.setId(rs.getInt("id"));
            orderData.setOrderName(rs.getString("order_name"));
            orderData.setCustomerName(rs.getString("customer_name"));
            orderData.setAddress(rs.getString("address"));
            return orderData;
        }
    }
}

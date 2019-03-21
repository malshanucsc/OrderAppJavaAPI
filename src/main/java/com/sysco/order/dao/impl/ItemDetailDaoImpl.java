package com.sysco.order.dao.impl;

import com.sysco.order.dao.ItemDetailDao;
import com.sysco.order.exception.EmptyItemException;
import com.sysco.order.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object implementation for Item entity
 *
 * @author mwan5534 on 3/21/19
 */
@Repository("ItemDetailDao")
public class ItemDetailDaoImpl implements ItemDetailDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Item> getAllItems() {

        String query = "SELECT * FROM item ";
        try {
            return jdbcTemplate.query(query, new ItemRowMapper());
        } catch (Exception e) {
            throw new EmptyItemException("no items",e);
        }
    }

    @Override
    public Item getSingleItem(String id){
        String query = "SELECT * FROM item where id = ?";
        try{
            return jdbcTemplate.queryForObject(query, new Object[] { id },new ItemRowMapper());
        }catch (Exception e){
            throw new EmptyItemException("item not available",e);
        }
    }


    public class ItemRowMapper implements RowMapper<Item> {
        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setItemName(rs.getString("name"));
            item.setUnit(rs.getString("unit"));
            item.setUnitValue(rs.getDouble("unitvalue"));
            return item;
        }
    }
}
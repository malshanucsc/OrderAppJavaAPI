package com.sysco.order.dao.impl;

import com.sysco.order.dao.UserDetailDao;
import com.sysco.order.exception.EmptyUserException;
import com.sysco.order.exception.UserCreationException;
import com.sysco.order.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object implementation for User entity
 *
 * @author mwan5534 on 3/21/19
 */
@Repository("UserDetailDao")
public class UserDetailDaoImpl implements UserDetailDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findByUserName(String userName){
        String query = "SELECT * FROM user WHERE username = ?";
        try{
            return jdbcTemplate.queryForObject(query, new Object[] { userName },new UserRowMapper());
        }catch (Exception e){
            throw new EmptyUserException("No user available",e);
        }
    }

    @Override
    public  Boolean createUser(String userName, String password){
        String query = "INSERT INTO user (username, password) VALUES(?, ?)";
        try {
            jdbcTemplate.update(query, userName, password);
            return true;
        } catch (Exception e) {
            throw new UserCreationException("user save error",e);
        }
    }

    public class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setUserName(rs.getString("username"));
            user.setEncodedPassword(rs.getString("password"));
            return user;
        }
    }
}

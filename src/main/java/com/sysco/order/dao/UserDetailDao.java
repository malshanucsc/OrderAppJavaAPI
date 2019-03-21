package com.sysco.order.dao;

import com.sysco.order.model.User;

/**
 * Data Access Object for User entity
 *
 * @author mwan5534 on 3/21/19
 */
public interface UserDetailDao {

    /**
     *find user by username
     * @param userName
     * @return User
     */
    User findByUserName(String userName);

    /**
     * create a user
     * @param username
     * @param password
     * @return boolean
     */
    Boolean createUser(String username, String password);

}

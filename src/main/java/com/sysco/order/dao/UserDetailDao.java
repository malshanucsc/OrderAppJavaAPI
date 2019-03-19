package com.sysco.order.dao;

import com.sysco.order.model.User;

public interface UserDetailDao {

    User findByUserName(String userName);

    Boolean createUser(String username, String password);

}

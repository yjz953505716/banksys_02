package com.qfedu.dao;

import com.qfedu.entity.User;

public interface UserDao {

    public User findByCode(String code);

    public void updateUser(User user);

    public User findByPass(String password);
}

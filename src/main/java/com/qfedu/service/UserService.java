package com.qfedu.service;

import com.qfedu.entity.User;

public interface UserService {

    public User loginfo(String code,String password);

    public Double findBalance(String code);

    public void updatePass(String code,String password,String newPass,String newTowPass);
}

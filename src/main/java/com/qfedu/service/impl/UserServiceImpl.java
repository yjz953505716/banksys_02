package com.qfedu.service.impl;

import com.qfedu.dao.UserDao;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User loginfo(String code, String password) {
        User user = userDao.findByCode(code);
        if (user == null){
            throw new RuntimeException("卡号错误");
        }
        if (!user.getPassword().equals(password)){
            throw new RuntimeException("密码错误");
        }
        return user;
    }

    @Override
    public Double findBalance(String code) {
        User user = userDao.findByCode(code);
        if (user == null){
            throw new RuntimeException("还未登录");
        }
        return user.getBalance();
    }

    @Override
    public void updatePass(String code,String password, String newPass, String newTowPass) {
        User byCode = userDao.findByCode(code);
        User user = userDao.findByPass(password);
        if (user == null && byCode != user){
            throw new RuntimeException("现密码错误");
        }
        if (!newPass.equals(newTowPass)){
            throw new RuntimeException("再次密码错误");
        }
        byCode.setPassword(newPass);
        userDao.updateUser(byCode);
    }
}

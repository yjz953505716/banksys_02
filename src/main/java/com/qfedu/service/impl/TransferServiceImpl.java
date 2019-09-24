package com.qfedu.service.impl;

import com.qfedu.dao.TransferDao;
import com.qfedu.dao.UserDao;
import com.qfedu.entity.Transfer;
import com.qfedu.entity.User;
import com.qfedu.service.TransferService;
import com.qfedu.vo.TransferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransferDao transferDao;

    @Autowired
    private UserDao userDao;

    /**
     * 转账方法同时生成记录并修改user表中的数据
     * @param sourceCode
     * @param descCode
     * @param money
     */
    @Override
    public void transfer(String sourceCode, String descCode, Double money) {

        User sUser = userDao.findByCode(sourceCode);
        User dUser = userDao.findByCode(descCode);

        if (dUser == null){
            throw new RuntimeException("卡号错误");
        }
        if (sUser.getBankCode().equals(descCode)){
            throw new RuntimeException("卡号不能相同");
        }
        if (sUser.getBalance() < money){
            throw new RuntimeException("余额不足");
        }

        //生成记录
        Transfer sTransfer = new Transfer();
        sTransfer.setUid(sUser.getUid());
        sTransfer.setMoney(0 - money);
        sTransfer.setBalance(sUser.getBalance() - money);
        transferDao.add(sTransfer);
        //并修改user表中的数据
        sUser.setBalance(sUser.getBalance() - money);
        userDao.updateUser(sUser);

        Transfer dTransfer = new Transfer();
        dTransfer.setUid(dUser.getUid());
        dTransfer.setMoney(money);
        dTransfer.setBalance(dUser.getBalance() + money);
        transferDao.add(dTransfer);
        dUser.setBalance(dUser.getBalance() + money);
        userDao.updateUser(dUser);
    }

    @Override
    public List<TransferVO> findAll(Integer uid, Date beginTime, Date endTime) {
        return transferDao.findAll(uid,beginTime,endTime);
    }
}

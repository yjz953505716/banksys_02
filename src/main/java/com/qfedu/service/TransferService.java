package com.qfedu.service;


import com.qfedu.vo.TransferVO;

import java.util.Date;
import java.util.List;

public interface TransferService {

    //转账
    public void transfer(String sourceCode, String descCode, Double money);

    public List<TransferVO> findAll(Integer uid, Date beginTime, Date endTime);

}

package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.TransferService;
import com.qfedu.utils.StrUtils;
import com.qfedu.vo.TransferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    @RequestMapping("/transfer.do")
    public JsonResult transfer(String descCode, Double money, HttpSession session){
        User user = (User) session.getAttribute(StrUtils.LOGIN_USER);
        if (user == null){
            return new JsonResult(1,"未登录");
        }
        transferService.transfer(user.getBankCode(),descCode,money);
        return new JsonResult(0,"转账成功");
    }

    @RequestMapping("/transferInfo.do")
    public JsonResult transferInfo(@DateTimeFormat(pattern = "yyyy-MM-dd")Date beginTime,@DateTimeFormat(pattern = "yyyy-MM-dd")Date endTime,HttpSession session){
        User user = (User) session.getAttribute(StrUtils.LOGIN_USER);
        if (user == null){
            return new JsonResult(1,"未登录");
        }
        List<TransferVO> voList = transferService.findAll(user.getUid(), beginTime, endTime);
        return new JsonResult(0,voList);
    }
}

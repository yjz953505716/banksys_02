package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    public JsonResult login(String code, String password, HttpSession session){
        User user = userService.loginfo(code, password);
        if (user == null){
            return new JsonResult(1,"登录失败");
        }
        session.setAttribute(StrUtils.LOGIN_USER,user);
        return new JsonResult(0,null);
    }

    @RequestMapping("/exit.do")
    public JsonResult exit(HttpSession session){
        session.removeAttribute(StrUtils.LOGIN_USER);
        session.invalidate();
        return new JsonResult(0,null);
    }

    @RequestMapping("/UserCode.do")
    public JsonResult UserCode(HttpSession session){
        User user = (User) session.getAttribute(StrUtils.LOGIN_USER);
        if (user == null){
            return new JsonResult(1,"还未登录");
        }
        return new JsonResult(0,user.getBankCode());
    }

    @RequestMapping("/balance.do")
    public JsonResult balance(HttpSession session){
        User user = (User) session.getAttribute(StrUtils.LOGIN_USER);
        if (user == null){
            return new JsonResult(1,"还未登录");
        }
        return new JsonResult(0,user.getBalance());
    }

    @RequestMapping("/upPass.do")
    public JsonResult upPass(String password, String newPass, String newTowPass,HttpSession session){
        User user = (User) session.getAttribute(StrUtils.LOGIN_USER);
        if (user == null){
            return new JsonResult(1,"还未登录");
        }
        userService.updatePass(user.getBankCode(),password,newPass,newTowPass);
        return new JsonResult(0,"修改成功");
    }
}

package com.ren.controller;

import com.ren.bean.Msg;
import com.ren.bean.SpringBeanFactoryUtils;
import com.ren.bean.User;
import com.ren.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class LoginVerify {

    @Autowired
    LoginService loginService;

    @Autowired
    Date date;
    /**
     * 跳转到登录页面
     * */
    @RequestMapping("/login")
    @ResponseBody
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }


    /**
     * 登录用户的请求
     * */
    @RequestMapping(value="/loginVerify",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView loginVerify(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord){
        User user = loginService.loginServiceVerify(userName,passWord);
        ModelAndView modelAndView = new ModelAndView();
        Date date = (Date)SpringBeanFactoryUtils.getBean("Date");// 获得系统时间.
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd-HH:mm:ss ");//规定时间的标准格式
        String loginTime = sdf.format(date);
        if(user != null){
            modelAndView.setViewName("home");
            modelAndView.addObject("status", Msg.success());
            loginService.insertLoginInfo(userName,loginTime);
        }else{
            modelAndView.setViewName("login");
            modelAndView.addObject("status", Msg.fail());
        }
        return modelAndView;
    }
}

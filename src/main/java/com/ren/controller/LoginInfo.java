package com.ren.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ren.bean.LoginInformation;
import com.ren.bean.Msg;
import com.ren.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginInfo {
    @Autowired
    LoginService loginService;

    //跳转到登录信息的页面
    @RequestMapping(value = "/loginInfoJump",method = RequestMethod.GET)
    public ModelAndView loginInfoJump(){
        return  new ModelAndView("loginRecords");
    }
    //显示登录的信息
    @RequestMapping(value = "/loginInfo",method=RequestMethod.GET)
    @ResponseBody
    public Msg loginInfo(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        PageHelper.startPage(pn, 5);
        List<LoginInformation> list;
        list = loginService.loginInfoService();
        PageInfo<LoginInformation> page = new PageInfo<>(list, 5);
        return Msg.success().add("pageInfo", page);
    }
}

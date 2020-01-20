package com.ren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BackToHome {
    /**
     * 从登录信息展示页面返回主页面
     * */
    @RequestMapping(value="/backToHome",method = RequestMethod.GET)
    public ModelAndView backToHome(){
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }
}

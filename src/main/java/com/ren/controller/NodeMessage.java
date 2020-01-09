package com.ren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NodeMessage {
    @RequestMapping("/nodeMessage")
    public ModelAndView nodeMessage(){
        ModelAndView modelAndView = new ModelAndView("nodeMessage");
        return modelAndView;
    }
}

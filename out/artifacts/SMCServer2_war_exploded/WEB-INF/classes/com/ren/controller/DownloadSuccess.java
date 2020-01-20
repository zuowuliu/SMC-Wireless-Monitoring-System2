package com.ren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DownloadSuccess {

    //考虑要不要设置一个显示下载成功的页面,或者直接alert?
    @RequestMapping(value = "/downloadSuccess")
    public ModelAndView fileDownload(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("status", "success");
        modelAndView.setViewName("downloadSuccess");
        return modelAndView;
    }
}

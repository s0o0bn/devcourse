package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/forward")
public class ForwardController {
    @GetMapping("/mav")
    public ModelAndView mav() {
        ModelAndView view = new ModelAndView();
        view.setViewName("mav");
        view.addObject("msg", "Forwarded Data");
        view.addObject("current time", new Date());
        return view;
    }
}

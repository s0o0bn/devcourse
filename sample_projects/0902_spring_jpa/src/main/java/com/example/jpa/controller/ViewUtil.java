package com.example.jpa.controller;

import jakarta.persistence.EntityManager;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.web.servlet.ModelAndView;

public class ViewUtil {
    private ViewUtil() {
    }

    public static ModelAndView getAlertView(String msg, String path) {
        ModelAndView mv = new ModelAndView("alert");
        mv.addObject("msg", msg);
        mv.addObject("path", path);
        return mv;
    }
}

package com.example.board.global.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LoginUserControllerAdvice {
    @ModelAttribute
    public void setLoginUser(Model model, HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        if (loginId != null) {
            model.addAttribute("loginId", loginId);
        }
    }
}

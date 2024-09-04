package com.example.jpa.controller;

import com.example.jpa.domain.dto.LoginDTO;
import com.example.jpa.domain.dto.SignupDTO;
import com.example.jpa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final UserService service;

    @GetMapping("/login")
    public String loginForm() {
        return "login_form";
    }

    @GetMapping("/signup-form")
    public String signupForm() {
        return "signup_form";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return "main";
    }

    @PostMapping(value = "/login")
    public ModelAndView login(HttpSession session, LoginDTO params) {
        String loggedUser = service.login(params.userId(), params.password());
        session.setAttribute("loginId", loggedUser);

        return ViewUtil.getAlertView("로그인 성공", "/main");
    }

    @PostMapping("signup")
    public ModelAndView signup(SignupDTO params) {
        service.signup(params);

        return ViewUtil.getAlertView("회원가입 성공", "/main");
    }
}

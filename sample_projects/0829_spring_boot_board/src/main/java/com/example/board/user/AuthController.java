package com.example.board.user;

import com.example.board.user.dto.LoginParams;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @GetMapping("/login-form")
    public String getLoginView() {
        return "login-form";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Void> login(@RequestBody LoginParams params, HttpSession session) {
        String loginUser = service.login(params.username(), params.password());
        session.setAttribute("loginId", loginUser);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/logout")
    @ResponseBody
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();

        return ResponseEntity.noContent().build();
    }
}

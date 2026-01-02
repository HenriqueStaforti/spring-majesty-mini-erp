package com.henrique.majesty.controller.auth;

import com.henrique.majesty.dto.auth.LoginDto;
import com.henrique.majesty.dto.auth.RegisterDto;
import com.henrique.majesty.dto.auth.TokenDto;
import com.henrique.majesty.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto body) {
        TokenDto token = authService.login(body);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDto body) {
        authService.register(body);
        return ResponseEntity.status(201).build();
    }
}

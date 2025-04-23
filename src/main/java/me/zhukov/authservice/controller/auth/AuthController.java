package me.zhukov.authservice.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.zhukov.authservice.model.dto.LoginRequest;
import me.zhukov.authservice.model.dto.RegisterRequest;
import me.zhukov.authservice.security.dto.JwtAuthenticationResponse;
import me.zhukov.authservice.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public JwtAuthenticationResponse register(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}

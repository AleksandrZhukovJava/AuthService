package me.zhukov.authservice.service;

import me.zhukov.authservice.model.dto.LoginRequest;
import me.zhukov.authservice.model.dto.RegisterRequest;
import me.zhukov.authservice.security.dto.JwtAuthenticationResponse;

public interface AuthService {
    JwtAuthenticationResponse register(RegisterRequest request);

    JwtAuthenticationResponse login(LoginRequest request);
}

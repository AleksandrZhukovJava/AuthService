package me.zhukov.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhukov.authservice.model.UserEntity;
import me.zhukov.authservice.model.dto.LoginRequest;
import me.zhukov.authservice.model.dto.RegisterRequest;
import me.zhukov.authservice.repository.UserRepository;
import me.zhukov.authservice.security.JwtService;
import me.zhukov.authservice.security.dto.JwtAuthenticationResponse;
import me.zhukov.authservice.security.dto.UserSecurityDto;
import me.zhukov.authservice.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static me.zhukov.authservice.model.enums.Role.USER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public JwtAuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("est takoi"); //todo
        }

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(new UserSecurityDto(user));
        return new JwtAuthenticationResponse(token);
    }

    @Override
    public JwtAuthenticationResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("net takogo chela"));//todo

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("parol govna"); //todo
        }

        String token = jwtService.generateToken(new UserSecurityDto(user));
        return new JwtAuthenticationResponse(token);
    }
}

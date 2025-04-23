package me.zhukov.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhukov.authservice.model.UserEntity;
import me.zhukov.authservice.repository.UserRepository;
import me.zhukov.authservice.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserEntity findByUsername(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(); //todo
    }

    @Override
    public UserEntity getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUsername(username);
    }
}

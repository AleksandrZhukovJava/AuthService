package me.zhukov.authservice.service;

import me.zhukov.authservice.model.UserEntity;

public interface UserService {
    UserEntity findByUsername(String userName);

    UserEntity getCurrentUser();
}

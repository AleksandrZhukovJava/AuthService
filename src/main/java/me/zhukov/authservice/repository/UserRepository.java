package me.zhukov.authservice.repository;

import me.zhukov.authservice.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String userName);

    boolean existsByUsername(String userName);
}

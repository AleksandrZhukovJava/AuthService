package me.zhukov.authservice.security;

import lombok.RequiredArgsConstructor;
import me.zhukov.authservice.repository.UserRepository;
import me.zhukov.authservice.security.dto.UserSecurityDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserSecurityDto(userRepository.findByUsername(username).orElseThrow()); //todo
    }
}

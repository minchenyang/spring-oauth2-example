package com.min.authorization.service.service;

import com.min.authorization.service.model.SignInIdentity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author mcy
 * @version V1.0
 * @Package com.min.oauth2.service.service.security
 * @date 2021/4/26 17:18
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SignInIdentity signInIdentity = new SignInIdentity();
        signInIdentity.setUsername("root");
        signInIdentity.setPassword(passwordEncoder.encode("123456"));
        signInIdentity.setIsValid(1);
        return signInIdentity;
    }
}

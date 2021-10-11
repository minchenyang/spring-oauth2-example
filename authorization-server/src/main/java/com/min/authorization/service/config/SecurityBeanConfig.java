package com.min.authorization.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * @author mcy
 * @version V1.0
 * @Package com.min.authorization.service.config
 * @date 2021/4/27 11:02
 */
@Configuration
public class SecurityBeanConfig {

    @Bean
    public KeyPair keyPair() throws Exception {
        ClassPathResource ksFile = new ClassPathResource("min-jwt.jks");
        KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(ksFile, "3327ZGg3327".toCharArray());
        return ksFactory.getKeyPair("min-oauth-jwt");
    }

    /**
     * 密码校验器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

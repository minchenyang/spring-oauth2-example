package com.min.authorization.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;

/**
 * 配置忽略路径
 * @author mcy
 * @version V1.0
 * @Package com.min.authorization.service.config
 * @date 2021/4/27 10:36
 */
@Order(1)
@Configuration
public class JwkSetEndpointConfiguration extends AuthorizationServerSecurityConfiguration {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers(req -> req.mvcMatchers("/.well-known/jwks.json"))
                .authorizeRequests(req -> req.mvcMatchers("/.well-known/jwks.json").permitAll());
    }
}

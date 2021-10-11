package com.min.authorization.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author mcy
 * @version V1.0
 * @Package PACKAGE_NAME
 * @date 2021/9/26 10:52
 */
@SpringBootTest
@Slf4j
public class AuthorizationTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private Filter springSecurityFilterChain;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    /**
     * 获取公钥
     */
    @Test
    void jwk() throws Exception {
        String contentAsString = mockMvc.perform(
                get("/.well-known/jwks.json")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andReturn().getResponse().getContentAsString();
        log.info("\n" + contentAsString);
    }

    /**
     * 密码模式
     */
    @Test
    void password() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("grant_type", "password");
        httpHeaders.add("username", "root");
        httpHeaders.add("password", "123456");

        // BASE64 编码的 账号:密码
        String token = "Basic d2ViLWNsaWVudDoxMjM0NTY=";
        String contentAsString = mockMvc.perform(
                post("/oauth/token")
                        .params(httpHeaders)
                        .header("Authorization", token)
        ).andReturn().getResponse().getContentAsString();
        log.info("\n" + contentAsString);
    }

    /**
     * 适合服务器
     * 秘钥模式
     * 没有刷新token
     */
    @Test
    void clientCredentials() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("grant_type", "client_credentials");
        // BASE64 编码的 账号:密码
        String token = "Basic d2ViLWNsaWVudDoxMjM0NTY=";
        String contentAsString = mockMvc.perform(
                post("/oauth/token")
                        .params(httpHeaders)
                        .header("Authorization", token)
        ).andReturn().getResponse().getContentAsString();
        log.info("\n" + contentAsString);
    }

    /**
     * 授权码模式
     * 如下
     * 请使用 浏览器 + postMan 尝试
     */
    @Test
    void code() throws Exception {
        // 1 浏览器请求此路径
        // state参数会原封不动的返回(方便校验)
        String url = "/oauth/authorize?response_type=code&client_id=web-client&redirect_uri=http://localhost:8082/login/oauth2/code/web-client-auth-code&state=123";
        // 2 输入账号密码
        // 3 确认授权
        // 4 回调页面 (会传回来 code 和 state  让后你在发如下请求~等待响应回调自己的服务


        // 通过 code 发送此请求
        // code 只能用一次
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("grant_type", "authorization_code");
        httpHeaders.add("client_id", "web-client");
        httpHeaders.add("client_secret", "123456");
        httpHeaders.add("redirect_uri", "http://localhost:8082/login/oauth2/code/web-client-auth-code");
        httpHeaders.add("code", "GrDz_a");
        String contentAsString = mockMvc.perform(
                post("/oauth/token")
                        .params(httpHeaders)
        ).andReturn().getResponse().getContentAsString();
        log.info("\n" + contentAsString);
    }


    /**
     * 刷新token
     */
    @Test
    void refresh() throws Exception {

        // 通过 code 发送此请求
        // code 只能用一次
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("grant_type", "refresh_token");
        httpHeaders.add("client_id", "web-client");
        httpHeaders.add("client_secret", "123456");
        httpHeaders.add("refresh_token", "xxxxxxxx");
        String contentAsString = mockMvc.perform(
                post("/oauth/token")
                        .params(httpHeaders)
        ).andReturn().getResponse().getContentAsString();
        log.info("\n" + contentAsString);
    }
}

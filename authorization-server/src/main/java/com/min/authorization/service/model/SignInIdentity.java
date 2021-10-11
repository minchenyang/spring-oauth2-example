package com.min.authorization.service.model;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author mcy
 * @version V1.0
 * @Package com.min.commons.mode.dto
 * @date 2021/4/26 17:22
 */
@Data
public class SignInIdentity implements UserDetails {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatarUrl;
    /**
     * 角色
     */
    private String roles;
    /**
     * 是否有效 0=无效 1=有效
     */
    private int isValid;
    /**
     * 角色集合, 不能为空
     */
    private List<GrantedAuthority> authorities;

    /**
     * 获取角色信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isValid == 0 ? false : true;
    }
}

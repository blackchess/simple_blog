package com.liaoxin;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class Oauth2User implements UserDetails {

    private Long id;

    private String account;

    private String password;

    private String phone;

    private String mail;

    private Integer role;

    private Integer status;

    private String nickName;

    private String iconUrl;

    private Date birthday;

    private List roles;

    private Date createTime;

    private Date updateTime;

    private Date lastTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

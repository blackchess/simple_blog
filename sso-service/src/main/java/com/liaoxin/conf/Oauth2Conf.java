package com.liaoxin.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


/**
 * @Auther: liaoxin
 * @Date: 2023/7/4
 * @Description: 认证服务器配置
 **/
@Configuration
@EnableAuthorizationServer
public class Oauth2Conf extends AuthorizationServerConfigurerAdapter {

    @Autowired
    TokenStore tokenStore;
    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                 .accessTokenConverter(jwtAccessTokenConverter);
    }

    /**
     * 客户端详情配置
     * @param clients
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("sso-admin").secret(passwordEncoder.encode("123456"))
                .scopes("all")
                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                .redirectUris("http://www.baidu.com")
                .resourceIds("article-service");
    }
}

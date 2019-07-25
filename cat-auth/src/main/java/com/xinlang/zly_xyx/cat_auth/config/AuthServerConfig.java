package com.xinlang.zly_xyx.cat_auth.config;

import com.xinlang.zly_xyx.cat_auth.service.impl.OnlyAuthenticationKeyGenerator;
import com.xinlang.zly_xyx.cat_auth.service.impl.RedisAuthCodeServices;
import com.xinlang.zly_xyx.cat_auth.service.impl.RedisClientDetailsService;
import com.xinlang.zly_xyx.user.LoginAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/25
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * jwt,redis
     * 默认redis
     */
    @Value("${access_token.store-jwt:false}")
    private boolean storeWithJwt;

    /**
     * 登录后返回的json数据是否追加当前用户信息
     * 默认false
     */
    @Value("${access_token.add-userinfo:false}")
    private boolean addUserInfo;

    /**
     * jwt签名key，可随意指定<br>
     * 如配置文件里不设置的话，冒号后面的是默认值
     */
    @Value("${access_token.jwt-signing-key:xiaoweijiagou}")
    private String signingKey;

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private RedisAuthCodeServices redisAuthCodeServices;
    @Autowired
    private RedisClientDetailsService redisClientDetailsService;
    @Autowired
    public UserDetailsService userDetailsService;

    /**
     * token存储
     */
    @Bean
    public TokenStore tokenStore(){
        if(storeWithJwt){
            return new JwtTokenStore(accessTokenConverter());
        }
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setAuthenticationKeyGenerator(new OnlyAuthenticationKeyGenerator());
        return redisTokenStore;
    }

    /**
     * Jwt资源令牌转换器<br>
     * 参数access_token.store-jwt为true时用到
     *
     * @return accessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                OAuth2AccessToken oAuth2AccessToken = super.enhance(accessToken, authentication);
                addLoginUserInfo(oAuth2AccessToken, authentication);
                return oAuth2AccessToken;
            }
        };
        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter
                .getAccessTokenConverter();
        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        userAuthenticationConverter.setUserDetailsService(userDetailsService);
        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        jwtAccessTokenConverter.setSigningKey(signingKey);
        return jwtAccessTokenConverter;
    }

    /**
     * 允许表单认证
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }

    /**
     * 将client信息保存到oauth_client_details表和redis中
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(redisClientDetailsService);
        redisClientDetailsService.loadAllClientToCache();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
        endpoints.tokenStore(tokenStore());
        endpoints.authorizationCodeServices(redisAuthCodeServices);
        if(storeWithJwt){
            endpoints.accessTokenConverter(accessTokenConverter());
        }else{
            //将当前用户信息加入到返回的json中
            endpoints.tokenEnhancer((accessToken,authentication)->{
                addLoginUserInfo(accessToken,authentication);
                return accessToken;
            });
        }
    }

    /**
     * 将当前用户信息追加到登陆后返回的json数据里<br>
     * 通过参数access_token.add-userinfo控制<br>
     * 2018.07.13
     *
     * @param accessToken
     * @param oAuth2Authentication
     */
    private void addLoginUserInfo(OAuth2AccessToken accessToken,OAuth2Authentication oAuth2Authentication){
        if(!addUserInfo){
            return;
        }
        if(accessToken instanceof DefaultOAuth2AccessToken){
            DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken)accessToken;
            Authentication authentication = oAuth2Authentication.getUserAuthentication();
            Object principal = authentication.getPrincipal();
            if(principal instanceof LoginAppUser){
                LoginAppUser loginAppUser = (LoginAppUser)principal;
                Map<String,Object> map = new HashMap<>(defaultOAuth2AccessToken.getAdditionalInformation());
                map.put("loginUser",loginAppUser);
                defaultOAuth2AccessToken.setAdditionalInformation(map);
            }
        }
    }
}

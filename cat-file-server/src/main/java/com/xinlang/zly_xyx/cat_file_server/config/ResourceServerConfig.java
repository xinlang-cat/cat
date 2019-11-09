package com.xinlang.zly_xyx.cat_file_server.config;

import com.xinlang.zly_xyx.cat_common.constant.PermitAllUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;

/**
 * resource server config
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * prefix
     */
    @Value("${file.local.prefix}")
    private String localFilePrefix;
    @Value("${album.local.prefix}")
    private String localAlbumPrefix;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling()
                .authenticationEntryPoint((request,response,authException)->response.sendError(HttpServletResponse.SC_ACCEPTED))
                .and().authorizeRequests()
                .antMatchers(PermitAllUrl.permitAllUrl("/flies-anon/**",localFilePrefix+"/**","/albums-anon/**",localAlbumPrefix+"/**")).permitAll()
                .anyRequest().authenticated().and().httpBasic();
    }
}

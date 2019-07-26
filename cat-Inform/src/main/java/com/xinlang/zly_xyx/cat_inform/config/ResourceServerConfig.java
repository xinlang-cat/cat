package com.xinlang.zly_xyx.cat_inform.config;

import com.xinlang.zly_xyx.cat_common.constant.PermitAllUrl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/26
 */
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling().authenticationEntryPoint((request,response,authException)->response.sendError(HttpServletResponse.SC_ACCEPTED))
                .and().authorizeRequests().antMatchers(PermitAllUrl.permitAllUrl("/inform-anon/**")).permitAll()
                .anyRequest().authenticated().and().httpBasic();
    }
}

package com.xinlang.cat_project.item.config;

import com.xinlang.zly_xyx.cat_common.constant.PermitAllUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * 资源服务配置
 * @author 张龙毅 18777811286@163.com
 * 2019/7/23
 */
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling().authenticationEntryPoint((request,response,authException)->response.sendError(HttpServletResponse.SC_ACCEPTED))
                .and().authorizeRequests().antMatchers(PermitAllUrl.permitAllUrl("/projectUser-anon/**")).permitAll()
                .anyRequest().authenticated().and().httpBasic();
    }
}

package com.xinlang.zly_xyx.cat_gateway_zuul.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/8/5
 */
@Configuration
public class CrossDNSConfig {

    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setMaxAge(18000L);
        config.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",config);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}

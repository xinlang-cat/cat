package com.xinlang.zly_xyx.cat_gateway_zuul.config;


import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 */
public class CrossDomainConfig {
    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);//cookies跨域
        config.addAllowedOrigin("*");//允许像该服务器提交请求的url，*表示全部允许
        config.addAllowedHeader("*");//允许访问信息头，*表示全部允许
        config.setMaxAge(180000L);//预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.addAllowedMethod("*");// 允许提交请求的方法，*表示全部允许
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);

    }
}

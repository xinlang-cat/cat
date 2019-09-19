package com.xinlang.zly_xyx.cat_inform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/26
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("inform   center swagger interface document")
                .apiInfo(new ApiInfoBuilder().title("inform center swagger interface document")
                        .contact(new Contact("zhanglongyi","","18777811286@163.com")).version("1.0")
                        .build()).select().paths(PathSelectors.any()).build();
    }
}
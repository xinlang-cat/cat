package com.xinlang.zly_xyx.cat_log.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger文档
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("日志中心XXXXXX接口文档")
				.apiInfo(new ApiInfoBuilder().title("日志中心XXXXX接口文档")
						.contact(new Contact("XXXX", "", "xxxxxxxxxx@163.com")).version("1.0").build())
				.select().paths(PathSelectors.any()).build();
	}
}

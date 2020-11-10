package org.mystfoo.controller;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 //启动swagger
public class SwaggerConfig {
    private ApiInfo createAi() {
        return new ApiInfoBuilder().title("标题：微服务").description("描述：微服务体验").version("版本：1.00.00").contact(new Contact("mystfoo", "", "1771274574@qq.com")).build();
    }

    @Bean
    public Docket createD() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createAi()).select().apis(RequestHandlerSelectors.basePackage("org.mystfoo.controller")).build();
    }
}

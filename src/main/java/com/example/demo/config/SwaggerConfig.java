package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/10/22
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                //将Date类型全部转为Long类型
                .directModelSubstitute(Date.class, Long.class)
                //将Timestamp类型全部转为Long类型
                .directModelSubstitute(Timestamp.class, Long.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        //联系信息
        Contact contact = new Contact(
                "zzsZhou",
                "https://github.com/zzsZhou",
                "");
        //api基本信息，展示在页面
        return new ApiInfoBuilder()
                .title("sharding-jdbc demo")
                .description("sharding-jdbc demo for spring boot")
                .termsOfServiceUrl("https://github.com/zzsZhou")
                .contact(contact)
                .version("0.1")
                .build();
    }
}

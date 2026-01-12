package com.qyt.qytbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置类
 * 用于配置SpringDoc OpenAPI
 *
 * @author qyt
 * @date 2024
 */
@Configuration
public class SwaggerConfig {

    /**
     * 配置OpenAPI信息
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("QYT Backend API")
                        .description("QYT后端服务API文档")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("QYT Team")
                                .email("support@qyt.com")
                                .url("http://www.qyt.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}

package com.inghub.credit.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gyurtalan
 * @version 1.0
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Credit Module Challenge")
                        .description("Credit Module Challenge")
                        .version("0.0.1-SNAPSHOT")
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")
                        )
                        .contact(new Contact()
                                .name("ING HUB")
                        )
                );
    }
}
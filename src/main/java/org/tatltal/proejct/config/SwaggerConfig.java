package org.tatltal.proejct.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.servers.Server;
import org.bson.types.ObjectId;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SwaggerConfig {
    static {

        SpringDocUtils.getConfig().replaceWithSchema(ObjectId.class, new StringSchema());
    }



    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Documentation")
                        .version("1.0")
                        .description("API Documentation with custom server"))
                .servers(Collections.singletonList(
                        new Server().url("http://138.2.112.144:8080/").description("Dev server")
                ));
    }

}

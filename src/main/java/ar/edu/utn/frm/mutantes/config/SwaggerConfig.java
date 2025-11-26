package ar.edu.utn.frm.mutantes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mutantes API - UTN FRM")
                        .version("1.0.0")
                        .description("Proyecto Integrador - Detector de Mutantes")
                        .contact(new Contact()
                                .name("Tomas Aranda")
                                .email("arandatomi10@gmail.com")
                                .url("https://github.com/arandatomi10")
                        )
                );
    }
}

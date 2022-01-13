package ornelas.tech.gerenciamentoOficina.core.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Aplicaçao para Gerenciamento de Oficina")
                        .description("Esta é uma aplicação Rest que faz o gerenciamento de aparelhos e clientes para uma oficina de eletrotécnica")
                        .termsOfService("terms").contact(new Contact().email("ornelaz95@gmail.com"))
                        .license(new License().name("GNU"))
                        .version("1.0.0"));
    }
}

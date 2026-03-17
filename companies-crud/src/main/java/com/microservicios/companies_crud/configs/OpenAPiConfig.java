package com.microservicios.companies_crud.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

// http://localhost:8081/companies-crud/swagger-ui/index.html
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Microservice companies crud",
                version = "1.0.0",
                description = "This is a CRUD for management companies"
        )
)
public class OpenAPiConfig {

}

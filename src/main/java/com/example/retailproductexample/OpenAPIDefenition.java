package com.example.retailproductexample;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Retail product public Api",
        description = "Retail product public API exposed for Retail-product-api",
        version = "1.0.0",
        termsOfService = "urn:tos",
        contact = @Contact(
            name = "Rand Aboudan",
            email = "rand.aboudan88@gmail.com"
        ),
        extensions = @Extension(properties = {
            @ExtensionProperty(name = "x-business-api-prospect", value = "false"),
            @ExtensionProperty(name = "x-business-api-compliance", value = "false")
        }),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0"
        )
    ),
    servers = @Server(
        url = "http://localhost:9090",
        description = "Retail Product API Server"
    )
)
class OpenApiConfiguration {}

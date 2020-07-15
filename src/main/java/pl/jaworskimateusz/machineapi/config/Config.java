package pl.jaworskimateusz.machineapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class Config {

    @Bean
    public Docket getSwaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.jaworskimateusz.machineapi"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("Machine Service API documentation",
                "Company database",
                "1.00",
                "https://www.linkedin.com/in/mateuszjaworski/",
                new Contact("Mateusz Jaworski", "https://www.linkedin.com/in/mateuszjaworski/", "jaworskimateuszm@gmail.com"),
                "",
                "",
                Collections.emptyList()
        );
    }

}

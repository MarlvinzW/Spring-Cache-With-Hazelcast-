package zw.dreamhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 11/08/2021
 */

@Configuration
@EnableSwagger2
public class SpringFox {
    @Bean
    public Docket apiDocumentationBean() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("zw.dreamhub"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .version("1.0")
                        .title("Spring Cache Example Application")
                        .contact(new Contact("Marlvin Chihota", "https://marlvinzw.me",
                                "hello@marlvinzw.me"))
                        .description("Documentation for Spring Cache application")
                        .build());
    }
}

package com.showmeyourcode.spring_cloud.standalone_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.showmeyourcode.spring_cloud.standalone_service.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Standalone Service API")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ornare quam tempus leo viverra, vehicula porta nisi pretium. Fusce pellentesque, ex eget consectetur vehicula, risus nulla lobortis dui, mollis vulputate nulla nisi et magna. Phasellus fermentum dapibus congue. Curabitur efficitur mi vitae nibh fermentum, et dignissim risus sollicitudin. ")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("CONTACT-NAME","URL","EMAIL"))
                .build();
    }
}

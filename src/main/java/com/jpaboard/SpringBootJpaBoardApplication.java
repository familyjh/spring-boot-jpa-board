package com.jpaboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class SpringBootJpaBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaBoardApplication.class, args);
	}
	
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Spring-Boot-JPA-Board")
                .apiInfo(apiInfo())
                .select()
                //.paths(regex("/greeting.*"))
                .paths(PathSelectors.ant("/apti/**"))
                .build();
    }
     
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("My Project API")
                .description("My Project API")
                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
                .contact("Niklas Heidloff")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

}

package com.sr.assesmentengine.microservice.configuration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static Logger logger=LogManager.getLogger("SwaggerConfig.class");
    public Docket productApi() {
    	logger.debug("SwaggerConfiguration");
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.wipro.SpringRest.controllers"))
                .paths(PathSelectors.any()).build();
    }
}
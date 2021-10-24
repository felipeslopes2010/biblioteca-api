package br.com.alura.biblioteca.infra;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxSwaggerConfigurations {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "API Livraria Java", 
	      "Bem-vindo(a) a minha Livraria", 
	      "Termos de Uso", 
	      "Termos de Serviço", 
	      new Contact("Felipe Lopes", "https://github.com/felipeslopes2010", "felipeslopes2010@hotmail.com"), 
	      "License of API", "API license URL", Collections.emptyList());   
	}
	
}

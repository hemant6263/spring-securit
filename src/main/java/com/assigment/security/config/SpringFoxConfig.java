package com.assigment.security.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.assigment.security"))
				.paths(PathSelectors.any()).build().apiInfo(apiEndPointsInfo())
				.globalOperationParameters(getImplicitPrams()).useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, getResponseMessages())
				.globalResponseMessage(RequestMethod.POST, getResponseMessages()).pathMapping("/");
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Example Demo Security").description("assigment")
				.contact(new Contact("Hemanta Singh", "", "hemanta6263@gmail.com"))
				.build();

	}

	private java.util.List<ResponseMessage> getResponseMessages() {
		java.util.List<ResponseMessage> list = new ArrayList<>();
		list.add(new ResponseMessageBuilder().code(200).message("request processed").build());
		list.add(new ResponseMessageBuilder().code(201).message("something went wrong").build());
		list.add(new ResponseMessageBuilder().code(401).message("Unauthorized").build());
		list.add(new ResponseMessageBuilder().code(500).message("Internal Server Error").build());
		list.add(new ResponseMessageBuilder().code(403).message("Not allowed").build());
		list.add(new ResponseMessageBuilder().code(409).message("Duplicate Request").build());
		return list;

	}

	private java.util.List<Parameter> getImplicitPrams() {
		java.util.List<Parameter> aParameters = new ArrayList<Parameter>();
		aParameters.add(new ParameterBuilder().name("Authoriztion").modelRef(new ModelRef("string"))
				.parameterType("header").defaultValue("Bearer - ").build());
		return aParameters;

	}

}
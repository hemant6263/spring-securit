package com.assigment.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
@Configuration
@ComponentScan({ "com.assigment.security" })
@EntityScan("com.assigment.security.entity")
@EnableJpaRepositories("com.assigmnet.security.repo")
public class SpringSecurityJwtApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	@Bean
	public Counter createdProfilesCounter(MeterRegistry registry) {
		return Counter.builder("api.token.created").description("Amount of token created").register(registry);
	}
}
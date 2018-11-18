package com.landl.hcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableJpaAuditing
public class HcareApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(HcareApplication.class, args);
		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/patients").allowedOrigins("http://localhost:8081");
				registry.addMapping("/patients/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalHistories").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalHistories/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalHistory").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalHistory/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/attachment").allowedOrigins("http://localhost:8081");
				registry.addMapping("/uploadAttachment").allowedOrigins("http://localhost:8081");
				registry.addMapping("/downloadAttachment").allowedOrigins("http://localhost:8081");
				registry.addMapping("/downloadAttachment/*").allowedOrigins("http://localhost:8081");
			}
		};
	}
}

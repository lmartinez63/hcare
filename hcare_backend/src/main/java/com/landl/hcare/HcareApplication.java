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
				registry.addMapping("/employees").allowedOrigins("http://localhost:8081");
				registry.addMapping("/employees/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/employeesByTitle").allowedOrigins("http://localhost:8081");
				registry.addMapping("/employeesByTitle/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/patients").allowedOrigins("http://localhost:8081");
				registry.addMapping("/patients/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalHistories").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalHistories/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalHistory").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalHistory/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalArea").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalArea/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalAreas").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalAreas/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalAppointmentsToday").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalAppointmentsToday/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalAppointments").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalAppointments/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalAppointmentsByPatientId").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalAppointmentsByPatientId/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalAppointment").allowedOrigins("http://localhost:8081");
				registry.addMapping("/medicalAppointment/*").allowedOrigins("http://localhost:8081");
				registry.addMapping("/attachment").allowedOrigins("http://localhost:8081");
				registry.addMapping("/uploadAttachment").allowedOrigins("http://localhost:8081");
				registry.addMapping("/downloadAttachment").allowedOrigins("http://localhost:8081");
				registry.addMapping("/downloadAttachment/*").allowedOrigins("http://localhost:8081");
			}
		};
	}
}

package com.landl.hcare;

import com.landl.hcare.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
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
			@Autowired
			PropertyService propertyService;

			@Override
			public void addCorsMappings(CorsRegistry registry) {

				String frontEndUrl = (String)propertyService.getPropertiesMap().get("frontEndUrl");
				registry.addMapping("/employees").allowedOrigins(frontEndUrl);
				registry.addMapping("/employees/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/employeesByTitle").allowedOrigins(frontEndUrl);
				registry.addMapping("/employeesByTitle/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/patients").allowedOrigins(frontEndUrl);
				registry.addMapping("/patients/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/retrievePatientByDocumentNumber/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalHistories").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalHistories/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalHistory").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalHistory/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalArea").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalArea/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalAreas").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalAreas/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalAppointmentsToday").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalAppointmentsToday/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalAppointments").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalAppointments/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalAppointmentsByPatientId").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalAppointmentsByPatientId/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalAppointment").allowedOrigins(frontEndUrl);
				registry.addMapping("/medicalAppointment/*").allowedOrigins(frontEndUrl);
				registry.addMapping("/attachment").allowedOrigins(frontEndUrl);
				registry.addMapping("/uploadAttachment").allowedOrigins(frontEndUrl);
				registry.addMapping("/downloadAttachment").allowedOrigins(frontEndUrl);
				registry.addMapping("/downloadAttachment/*").allowedOrigins(frontEndUrl);
			}
		};
	}
}

package com.projeto.dentalhelper;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import com.projeto.dentalhelper.config.property.DentalHelperApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(DentalHelperApiProperty.class)
public class DentalHelperApplication {

	private static ApplicationContext APPLICATION_CONTEXT;

	public static void main(String[] args) {
		APPLICATION_CONTEXT = SpringApplication.run(DentalHelperApplication.class, args);
	}

	public static <T> T getBean(Class<T> type) {
		return APPLICATION_CONTEXT.getBean(type);
	}

//	@PostConstruct
//	void started() {
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//	}

}

package com.projeto.dentalhelper;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DentalHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(DentalHelperApplication.class, args);
	}
	
	@PostConstruct
	  void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	  }

}


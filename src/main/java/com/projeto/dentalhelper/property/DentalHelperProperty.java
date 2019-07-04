package com.projeto.dentalhelper.property;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

// TODO: Corrigir erro de autowired no CorsFilter 
@ConfigurationProperties("dental-helper")
public class DentalHelperProperty {
	
	private List<String> originsPermitidas = Arrays.asList("http://localhost:4200", "http://localhost:8100",
			"https://dentalhelper.herokuapp.com");

	public List<String> getOriginsPermitidas() {
		return originsPermitidas;
	}

	public void setOriginsPermitidas(List<String> originsPermitidas) {
		this.originsPermitidas = originsPermitidas;
	}
	

}

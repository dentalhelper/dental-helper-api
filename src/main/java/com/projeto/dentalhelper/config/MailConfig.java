package com.projeto.dentalhelper.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.projeto.dentalhelper.property.DentalHelperProperty;

@Configuration
public class MailConfig {
	
//	@Autowired
//	private DentalHelperProperty property;
	
//	dental-helper.mail.host=smtp.gmail.com
//			dental-helper.mail.port=587
//			dental-helper.mail.username=dental.helper.mail
//			dental-helper.mail.password=d3ntalh3lp3r

	@Bean
	public JavaMailSender javaMailSender() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.connectiontimeout", 10000);
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setJavaMailProperties(props);
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("dental.helper.mail");
		mailSender.setPassword("d3ntalh3lp3r");
		
		return mailSender;
	}
	
}

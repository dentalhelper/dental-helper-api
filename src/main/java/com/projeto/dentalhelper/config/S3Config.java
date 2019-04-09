package com.projeto.dentalhelper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.projeto.dentalhelper.config.property.DentalHelperApiProperty;

@Configuration
public class S3Config {

	@Autowired
	private DentalHelperApiProperty property;

	@Bean
	public AmazonS3 s3Client() {
		AWSCredentials credenciaisAWS = new BasicAWSCredentials(property.getS3().getSecretAccessKey(),
				property.getS3().getAccessKeyId());
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(property.getS3().getRegion()))
				.withCredentials(new AWSStaticCredentialsProvider(credenciaisAWS)).build();
		return s3Client;
	}

}
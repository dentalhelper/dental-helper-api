package com.projeto.dentalhelper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Tag;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.lifecycle.LifecycleFilter;
import com.amazonaws.services.s3.model.lifecycle.LifecycleTagPredicate;
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

		if (!s3Client.doesBucketExistV2(property.getS3().getBucket())) {
			s3Client.createBucket(new CreateBucketRequest(property.getS3().getBucket()));
			
			BucketLifecycleConfiguration.Rule regraExpiracao = new BucketLifecycleConfiguration.Rule()
					.withId("Regra de expiração de arquivos temporários")
					.withFilter(new LifecycleFilter(new LifecycleTagPredicate(new Tag("expirar", "true"))))
					.withExpirationInDays(1)
					.withStatus(BucketLifecycleConfiguration.ENABLED);

			BucketLifecycleConfiguration configuration = new BucketLifecycleConfiguration().withRules(regraExpiracao);

			s3Client.setBucketLifecycleConfiguration(property.getS3().getBucket(), configuration);
		}
		return s3Client;
	}

}
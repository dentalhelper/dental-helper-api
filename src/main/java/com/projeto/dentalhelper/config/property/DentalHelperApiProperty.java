package com.projeto.dentalhelper.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("dh")
public class DentalHelperApiProperty {

	private final S3 s3 = new S3();
	
	private final Imagem imagem = new Imagem();

	public S3 getS3() {
		return s3;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public static class S3 {
		private String accessKeyId;
		private String secretAccessKey;
		private String bucket = "dental-helper";
		private String region = "sa-east-1";

		public String getAccessKeyId() {
			return accessKeyId;
		}

		public void setAccessKeyId(String accessKeyId) {
			this.accessKeyId = accessKeyId;
		}

		public String getSecretAccessKey() {
			return secretAccessKey;
		}

		public void setSecretAccessKey(String secretAccessKey) {
			this.secretAccessKey = secretAccessKey;
		}

		public String getBucket() {
			return bucket;
		}

		public void setBucket(String bucket) {
			this.bucket = bucket;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

	}
	
	public static class Imagem{
		private Integer tamanho = 200;

		public Integer getTamanho() {
			return tamanho;
		}

		public void setTamanho(Integer tamanho) {
			this.tamanho = tamanho;
		}
		
	}
}

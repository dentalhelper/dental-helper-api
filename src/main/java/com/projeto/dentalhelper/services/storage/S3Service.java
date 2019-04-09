package com.projeto.dentalhelper.services.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.projeto.dentalhelper.config.property.DentalHelperApiProperty;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3Client;

	@Autowired
	private DentalHelperApiProperty property;

	public URI enviarArquivo(MultipartFile multipartFile) {
		try {
			String nomeDoArquivo = multipartFile.getOriginalFilename();
			InputStream inputStream = multipartFile.getInputStream();
			String tipoDoArquivo = multipartFile.getContentType();

			return enviarArquivo(inputStream, nomeDoArquivo, tipoDoArquivo);
		} catch (IOException e) {
			throw new RuntimeException("Erro de IO: " + e.getMessage());
		}
	}

	public URI enviarArquivo(InputStream inputStream, String nomeDoArquivo, String tipoDoArquivo) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(tipoDoArquivo);
			s3Client.putObject(property.getS3().getBucket(), nomeDoArquivo, inputStream, meta);
			return s3Client.getUrl(property.getS3().getBucket(), nomeDoArquivo).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}
	}
}

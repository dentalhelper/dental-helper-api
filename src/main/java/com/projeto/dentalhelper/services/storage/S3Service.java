package com.projeto.dentalhelper.services.storage;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.ObjectTagging;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.SetObjectTaggingRequest;
import com.amazonaws.services.s3.model.Tag;
import com.projeto.dentalhelper.config.property.DentalHelperApiProperty;
import com.projeto.dentalhelper.domains.Foto;
import com.projeto.dentalhelper.services.exceptions.FileException;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 amazonS3;

	@Autowired
	private ImageService imageService;

	@Autowired
	private DentalHelperApiProperty property;

	public Foto enviarArquivo(MultipartFile multipartFile) {
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);

		jpgImage = imageService.recortarImagem(jpgImage);
		jpgImage = imageService.redimensionarImagem(jpgImage, property.getImagem().getTamanho());

		String nomeDoArquivo = gerarNomeUnico(multipartFile.getOriginalFilename());
		InputStream inputStream = imageService.getInputStream(jpgImage, "jpg");
		String tipoDoArquivo = multipartFile.getContentType();
		return enviarArquivo(inputStream, nomeDoArquivo, tipoDoArquivo);

	}

	private String gerarNomeUnico(String originalFilename) {
		return UUID.randomUUID().toString() + "_" + originalFilename;
	}

	public Foto enviarArquivo(InputStream inputStream, String nomeDoArquivo, String tipoDoArquivo) {
		AccessControlList acl = new AccessControlList();

		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);

		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(tipoDoArquivo);

			PutObjectRequest putObjectRequest = new PutObjectRequest(property.getS3().getBucket(), nomeDoArquivo,
					inputStream, meta).withAccessControlList(acl);

			putObjectRequest.setTagging(new ObjectTagging(Arrays.asList(new Tag("expirar", "true"))));

			amazonS3.putObject(putObjectRequest);

			URI uri = amazonS3.getUrl(property.getS3().getBucket(), nomeDoArquivo).toURI();
			Foto foto = new Foto(uri, nomeDoArquivo);
			return foto;
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	}

	public void salvar(String objeto) {
		SetObjectTaggingRequest setObjectTaggingRequest = new SetObjectTaggingRequest(property.getS3().getBucket(),
				objeto, new ObjectTagging(Collections.emptyList()));

		amazonS3.setObjectTagging(setObjectTaggingRequest);
	}

	public void remover(String objeto) {
		DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(property.getS3().getBucket(), objeto);
		amazonS3.deleteObject(deleteObjectRequest);
	}

	public void substituir(String objetoAntigo, String objetoNovo) {
		if (StringUtils.hasText(objetoAntigo)) {
			this.remover(objetoAntigo);
		}

		salvar(objetoNovo);

	}
}

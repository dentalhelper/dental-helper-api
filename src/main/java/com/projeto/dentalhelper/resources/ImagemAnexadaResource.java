package com.projeto.dentalhelper.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.dentalhelper.domains.Foto;
import com.projeto.dentalhelper.domains.ImagemAnexada;
import com.projeto.dentalhelper.dtos.ImagensAnexadasNovoDTO;
import com.projeto.dentalhelper.repositories.ImagemAnexadaRepository;
import com.projeto.dentalhelper.resources.api.ImagemAnexadaApi;
import com.projeto.dentalhelper.services.ImagemAnexadaService;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class ImagemAnexadaResource extends AbstractResource<ImagemAnexada, ImagemAnexadaService> implements ImagemAnexadaApi{

	@Autowired
	private ImagemAnexadaRepository repository;
	

	@Override
	public ResponseEntity<ImagemAnexada> getByCodigo(Long codigo) {
		ImagemAnexada objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<ImagemAnexada> put (@PathVariable Long codigo, @Valid @RequestBody String descricao) throws ServiceApplicationException{
			ImagemAnexada objetoEditado = null;
			objetoEditado = service.atualizar(codigo, descricao);

		return ResponseEntity.ok(objetoEditado);
		
	}

	@Override
	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);

		return ResponseEntity.noContent().header("Entity", Long.toString(codigo)).build();
	}

	@Override
	public ResponseEntity<List<ImagemAnexada>> post(@Valid ImagensAnexadasNovoDTO objetoDTO, HttpServletResponse response) throws ServiceApplicationException {
		List<ImagemAnexada> imagensSalvas = service.salvarImagens(objetoDTO);
		for(ImagemAnexada i: imagensSalvas) {
			Long codigo = i.getCodigo();
			adicionarHeaderLocation(response, codigo, "/imagensAnexadas");
			adicionarLink(i, codigo);	
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(imagensSalvas);
		
		
	}

	@Override
	public ResponseEntity<Foto> postImage(MultipartFile file) {
		Foto foto = service.enviarFoto(file);
		return ResponseEntity.status(HttpStatus.CREATED).body(foto);
	}

	@Override
	public List<ImagemAnexada> getByCodigoProcedimentoPrevisto(Long codigo) {
		return repository.buscarPorCodigoDeProcedimentoPrevisto(codigo);
	}

}

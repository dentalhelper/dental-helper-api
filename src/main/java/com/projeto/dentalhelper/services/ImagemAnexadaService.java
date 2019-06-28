package com.projeto.dentalhelper.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.dentalhelper.domains.Foto;
import com.projeto.dentalhelper.domains.ImagemAnexada;
import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;
import com.projeto.dentalhelper.dtos.ImagemAnexadaNovoDTO;
import com.projeto.dentalhelper.dtos.ImagensAnexadasNovoDTO;
import com.projeto.dentalhelper.repositories.ImagemAnexadaRepository;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;
import com.projeto.dentalhelper.services.storage.S3Service;

@Service
public class ImagemAnexadaService extends AbstractService<ImagemAnexada, ImagemAnexadaRepository>{
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ProcedimentoPrevistoService procedimentoPrevistoService;
	
	
	public Foto enviarFoto(MultipartFile multipartFile) {
		return s3Service.enviarArquivo(multipartFile);
		
	}
	
	
	public List<ImagemAnexada> salvarImagens(ImagensAnexadasNovoDTO imagensDTO) throws ServiceApplicationException{
		List<ImagemAnexada> imagensAnexadas = fromDTO(imagensDTO);
		List<ImagemAnexada> imagensSalvas = new ArrayList<ImagemAnexada>();
		for(ImagemAnexada i: imagensAnexadas) {
			if(i.getDescricao() == null) {
				i.setDescricao("");
			}
			imagensSalvas.add(salvar(i));
		}
		return imagensSalvas;
	}
	
	
	@Override
	public ImagemAnexada salvar(ImagemAnexada objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
//		s3Service.salvar(objeto.getFoto());
		
		return repository.save(objeto);
	}
	
	

	public ImagemAnexada atualizar(Long codigo, String descricao) throws ServiceApplicationException {
		ImagemAnexada objetoAtualizado = buscarPorCodigo(codigo);
		
		if(descricao != null) {
			objetoAtualizado.setDescricao(descricao);
		}
		
		return repository.save(objetoAtualizado);
	}
	
	
	public void deletar (Long codigo)   {
		ImagemAnexada objetoAtualizado = buscarPorCodigo(codigo);
		
		repository.deleteById(codigo);
		

		s3Service.remover(objetoAtualizado.getFoto());


	}
	
	
	public List<ImagemAnexada> fromDTO(ImagensAnexadasNovoDTO imagensDTO){
		ProcedimentoPrevisto procedimentoPrevisto = procedimentoPrevistoService.buscarPorCodigo(imagensDTO.getCodigoProcedimentoPrevisto());
		
		List<ImagemAnexada> imagensAnexadas = new ArrayList<ImagemAnexada>();
		for(ImagemAnexadaNovoDTO imagemDTO: imagensDTO.getImagens()) {
			ImagemAnexada imagemAnexada = new ImagemAnexada(imagemDTO.getFoto(), imagemDTO.getUrlDaFoto(), procedimentoPrevisto, imagemDTO.getObservacao());
			imagensAnexadas.add(imagemAnexada);
		}
		
		return imagensAnexadas;
	}
	
	

}

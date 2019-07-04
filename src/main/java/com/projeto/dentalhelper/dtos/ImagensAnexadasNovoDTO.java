package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//classe para salvar várias imagens de uma vez
public class ImagensAnexadasNovoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long codigoProcedimentoPrevisto;
	
	@Valid
	@NotEmpty
	private List<ImagemAnexadaNovoDTO> imagens = new ArrayList<ImagemAnexadaNovoDTO>();
	
	

	public Long getCodigoProcedimentoPrevisto() {
		return codigoProcedimentoPrevisto;
	}

	public void setCodigoProcedimentoPrevisto(Long codigoProcedimentoPrevisto) {
		this.codigoProcedimentoPrevisto = codigoProcedimentoPrevisto;
	}

	public List<ImagemAnexadaNovoDTO> getImagens() {
		return imagens;
	}

	public void setImagens(List<ImagemAnexadaNovoDTO> imagens) {
		this.imagens = imagens;
	}
	
	
	
	

}

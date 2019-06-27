package com.projeto.dentalhelper.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

//classe para cada imagem a ser salva individualmente
public class ImagemAnexadaNovoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Long codigo;
	
	@NotBlank
	private String foto;
	@NotBlank
	private String urlDaFoto;

	private String observacao;
	
	
	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getUrlDaFoto() {
		return urlDaFoto;
	}

	public void setUrlDaFoto(String urlDaFoto) {
		this.urlDaFoto = urlDaFoto;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	

}

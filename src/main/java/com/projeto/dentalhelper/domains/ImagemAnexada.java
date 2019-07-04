package com.projeto.dentalhelper.domains;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "imagem_anexada")
public class ImagemAnexada extends ObjetoIdentificado{

	private static final long serialVersionUID = 1L;
	
	private String foto;

	@Column(name = "url_da_foto")
	private String urlDaFoto;
	
	@JsonIgnoreProperties({"imagensAnexadas","orcamento", "dentes"})
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "codigo_procedimento_previsto")
	private ProcedimentoPrevisto procedimentoPrevisto;
	
	private String descricao;


	public ImagemAnexada(String foto, String urlDaFoto, ProcedimentoPrevisto procedimentoPrevisto, String descricao) {
		this.foto = foto;
		this.urlDaFoto = urlDaFoto;
		this.procedimentoPrevisto = procedimentoPrevisto;
		this.descricao = descricao;
	}
	
	public ImagemAnexada() {
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

	public ProcedimentoPrevisto getProcedimentoPrevisto() {
		return procedimentoPrevisto;
	}

	public void setProcedimentoPrevisto(ProcedimentoPrevisto procedimentoPrevisto) {
		this.procedimentoPrevisto = procedimentoPrevisto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}

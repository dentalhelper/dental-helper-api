package com.projeto.dentalhelper.domains;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Entity
//@Table(name = "questao")
public class Questao extends ObjetoIdentificado{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private Boolean resposta;
	
	private String informacoesAdicionais;
	
	@ManyToOne
	@JoinColumn(name = "codigo_anamnese")
	private Anamnese anamnese;

	public String getDescricao() {
		return descricao;
	}

	public Boolean getResposta() {
		return resposta;
	}

	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	public Anamnese getAnamnese() {
		return anamnese;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setResposta(Boolean resposta) {
		this.resposta = resposta;
	}

	public void setInformacoesAdicionais(String informacoesAdicionais) {
		this.informacoesAdicionais = informacoesAdicionais;
	}

	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}
	
	
	

	

}

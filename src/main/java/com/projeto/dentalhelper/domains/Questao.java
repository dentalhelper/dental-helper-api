package com.projeto.dentalhelper.domains;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "questao")
public class Questao {
	
	@NotNull
	private String descricao;
	@NotNull
	private Boolean resposta;
	private String informacoesAdicionais;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getResposta() {
		return resposta;
	}
	public void setResposta(Boolean resposta) {
		this.resposta = resposta;
	}
	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}
	public void setInformacoesAdicionais(String informacoesAdicionais) {
		this.informacoesAdicionais = informacoesAdicionais;
	}
	
	

}

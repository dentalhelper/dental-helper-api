package com.projeto.dentalhelper.domains;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dente")
public class Dente extends ObjetoIdentificado{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Size(max = 50)
	private String nome;
	@NotNull
	private Short numero;
	private String observacao;
	
	private Boolean existente;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Short getNumero() {
		return numero;
	}
	public void setNumero(Short numero) {
		this.numero = numero;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Boolean getExistente() {
		return existente;
	}
	public void setExistente(Boolean existente) {
		this.existente = existente;
	}
	
	
}

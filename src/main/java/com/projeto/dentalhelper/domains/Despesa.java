package com.projeto.dentalhelper.domains;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "despesa")
public class Despesa extends ObjetoIdentificado{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Float valor;


	@Size( max = 50)
	private String descricao;

	
	@ManyToOne
	@JoinColumn(name = "codigo_categoria")
	private CategoriaDespesa categoria;
	

	@JoinColumn(name = "codigo_pagamento")
	@OneToOne(cascade = CascadeType.ALL)
	private Pagamento pagamento;

	public Float getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public CategoriaDespesa getCategoria() {
		return categoria;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCategoria(CategoriaDespesa categoria) {
		this.categoria = categoria;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	
	
	

}

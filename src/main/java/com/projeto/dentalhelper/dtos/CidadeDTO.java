package com.projeto.dentalhelper.dtos;

import java.io.Serializable;

import com.projeto.dentalhelper.domains.Cidade;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;

	private String nome;

	public CidadeDTO() {

	}

	public CidadeDTO(Cidade cidade) {
		this.codigo = cidade.getCodigo();
		this.nome = cidade.getNome();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

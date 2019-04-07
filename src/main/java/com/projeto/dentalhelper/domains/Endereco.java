package com.projeto.dentalhelper.domains;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "endereco")
public class Endereco extends ObjetoIdentificado{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String logradouro;
	

	private Integer numero;
	

	private String bairro;

	private String CEP;
	
	private String complemento;
	
	@ManyToOne
	@JoinColumn(name = "codigo_cidade")
	private Cidade cidade;
	
	public Endereco() {
		
	}
	
	public Endereco(String logradouro, Integer numero,
			String bairro, String cEP, String complemento, Cidade cidade) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		CEP = cEP;
		this.complemento = complemento;
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	

}

package com.projeto.dentalhelper.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "procedimento")
public class Procedimento extends ObjetoIdentificado{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(max = 50)
	private String nome;
	
	@NotNull
	@Column(name = "valor")
	private Float valorBase;
	
	@NotNull
	@Size(max = 50)
	private String descricao;
	
	@NotNull
	@Column(name = "duracao")
	private Integer duracaoMinutos;
	
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Float getValorBase() {
		return valorBase;
	}
	public Integer getDuracaoMinutos() {
		return duracaoMinutos;
	}
	public void setValorBase(Float valorBase) {
		this.valorBase = valorBase;
	}
	public void setDuracaoMinutos(Integer duracaoMinutos) {
		this.duracaoMinutos = duracaoMinutos;
	}


	
	
	
	
	
	

}

package com.projeto.dentalhelper.domains;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "atributo_material")
public class AtributoMaterial extends ObjetoIdentificado{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private String nome;
	@NotNull
	private String valor;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}

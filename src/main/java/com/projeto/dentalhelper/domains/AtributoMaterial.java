package com.projeto.dentalhelper.domains;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "atributo_material")
public class AtributoMaterial extends ObjetoIdentificado{
	
	private String nome;
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

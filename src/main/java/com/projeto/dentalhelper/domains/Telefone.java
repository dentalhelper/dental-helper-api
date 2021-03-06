package com.projeto.dentalhelper.domains;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "telefone")
public class Telefone extends ObjetoIdentificado {

	private static final long serialVersionUID = 1L;

	private String numero;

	@ManyToOne
	@JoinColumn(name = "codigo_pessoa")
	private Pessoa pessoa;

	public Telefone() {
		
	}
	
	public Telefone(String numero, Pessoa pessoa) {
		super();
		this.numero = numero;
		this.pessoa = pessoa;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}

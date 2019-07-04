package com.projeto.dentalhelper.dtos;

import java.io.Serializable;

public class ProcedimentoOdontogramaResumoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private Boolean finalizado;
	
	public ProcedimentoOdontogramaResumoDTO(String nome, Boolean finalizado) {	
		this.nome = nome;
		this.finalizado = finalizado;
	}
	
	
	public ProcedimentoOdontogramaResumoDTO() {
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Boolean getFinalizado() {
		return finalizado;
	}


	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}



}

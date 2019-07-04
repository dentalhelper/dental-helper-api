package com.projeto.dentalhelper.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioAlteraSenhaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String senhaAtual;
	
	@NotBlank
	@Size(min = 5, max = 50)
	private String novaSenha;
	
	public String getSenhaAtual() {
		return senhaAtual;
	}
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	
	

}

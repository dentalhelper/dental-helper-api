package com.projeto.dentalhelper.repositories.filter;

public class MaterialFilter {
	
	private String nome;
	private String fabricante;
	private String valorAtributo;
	
	public String getNome() {
		return nome;
	}
	public String getFabricante() {
		return fabricante;
	}
	public String getValorAtributo() {
		return valorAtributo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public void setValorAtributo(String valorAtributo) {
		this.valorAtributo = valorAtributo;
	}
	
	
}

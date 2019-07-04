package com.projeto.dentalhelper.domains.enums;

public enum FormaDoRosto {
	
	QUADRADO(1, "Quadrado"),
	REDONDO(2, "Redondo"),
	TRIANGULAR(3, "Triangular"),
	RETANGULAR(4, "Retangular");
	
	private int codigo;
	private String descricao;
	private FormaDoRosto(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static FormaDoRosto toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (FormaDoRosto forma : FormaDoRosto.values()) {
			if (codigo.equals(forma.getCodigo())) {
				return forma;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

}

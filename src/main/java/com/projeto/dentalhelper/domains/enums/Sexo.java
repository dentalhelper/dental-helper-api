package com.projeto.dentalhelper.domains.enums;

public enum Sexo {

	FEMININO(1, "Feminino"), 
	MASCULINO(2, "Masculino");

	private int codigo;
	private String descricao;

	private Sexo(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Sexo toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (Sexo sexo : Sexo.values()) {
			if (codigo.equals(sexo.getCodigo())) {
				return sexo;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}
}

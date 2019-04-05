package com.projeto.dentalhelper.domains.enums;

public enum EstadoCivil {

	SOLTEIRO(1, "Solteiro"), 
	CASADO(2, "Casado"), 
	VIUVO(3, "Viúvo"), 
	SEPARADO(4, "Separado"), 
	DIVORCIADO(5, "Divorciado");

	private int codigo;
	private String descricao;

	private EstadoCivil(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoCivil toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (EstadoCivil estadoCivil : EstadoCivil.values()) {
			if (codigo.equals(estadoCivil.getCodigo())) {
				return estadoCivil;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}
}

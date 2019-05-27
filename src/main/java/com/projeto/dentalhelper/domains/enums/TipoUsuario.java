package com.projeto.dentalhelper.domains.enums;

public enum TipoUsuario {
	
	ADMINISTRADOR(1, "Administrador"), ASSISTENTE(2, "Assistente"), RECEPCIONISTA(3, "Recepcionista");

	private int codigo;
	private String descricao;

	private TipoUsuario(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoUsuario toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (TipoUsuario tipo : TipoUsuario.values()) {
			if (codigo.equals(tipo.getCodigo())) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

}

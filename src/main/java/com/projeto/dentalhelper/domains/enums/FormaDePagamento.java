package com.projeto.dentalhelper.domains.enums;

public enum FormaDePagamento {

	DINHEIRO(1, "Dinheiro"), 
	CARTAO(2, "Cartão");

	private int codigo;
	private String descricao;

	private FormaDePagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static FormaDePagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (FormaDePagamento forma : FormaDePagamento.values()) {
			if (codigo.equals(forma.getCodigo())) {
				return forma;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

}

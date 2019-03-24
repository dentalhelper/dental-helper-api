package com.projeto.dentalhelper.domains.enums;

public enum TipoPagamento {

	DESPESA(1, "Despesa"), RECEBIMENTO(2, "Recebimento");

	private int codigo;
	private String descricao;

	private TipoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoPagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (TipoPagamento forma : TipoPagamento.values()) {
			if (codigo.equals(forma.getCodigo())) {
				return forma;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

}

package com.projeto.dentalhelper.domains.enums;

public enum StatusPagamento {
	ABERTO(1, "Agendado"),
	PAGO(2, "Confirmado"),
	CANCELADO(3, "Finalizado");
	
	private int codigo;
	private String descricao;
	private StatusPagamento(int codigo, String descricao) {
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
	
	public static StatusPagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (StatusPagamento status : StatusPagamento.values()) {
			if (codigo.equals(status.getCodigo())) {
				return status;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}
}

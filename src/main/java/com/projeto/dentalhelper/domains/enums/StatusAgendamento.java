package com.projeto.dentalhelper.domains.enums;

public enum StatusAgendamento {
	AGENDADO(1, "Agendado"),
	CONFIRMADO(2, "Confirmado"),
	FINALIZADO(3, "Finalizado"),
	FALTOU(4, "Faltou"),
	CANCELADO(5, "Cancelado");
	
	private int codigo;
	private String descricao;
	private StatusAgendamento(int codigo, String descricao) {
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
	
	public static StatusAgendamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (StatusAgendamento status : StatusAgendamento.values()) {
			if (codigo.equals(status.getCodigo())) {
				return status;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

}

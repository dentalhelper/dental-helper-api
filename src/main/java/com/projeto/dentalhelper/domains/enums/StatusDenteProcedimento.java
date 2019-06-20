package com.projeto.dentalhelper.domains.enums;

public enum StatusDenteProcedimento {
	
	SEM_TRATAMENTOS(1, "Sem tratamentos"),
	EM_ANDAMENTO(2, "Em andamento"),
	FINALIZADO(3, "Finalizado");
	
	private int codigo;
	private String descricao;
	private StatusDenteProcedimento(int codigo, String descricao) {
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
	
	public static StatusDenteProcedimento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (StatusDenteProcedimento status : StatusDenteProcedimento.values()) {
			if (codigo.equals(status.getCodigo())) {
				return status;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

}

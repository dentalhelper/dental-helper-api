package com.projeto.dentalhelper.domains.enums;

public enum RespostaQuestaoAnamnese {
	
	SIM(1, "Sim"),
	NAO(2, "Não"),
	NAO_SEI(3, "Não sei"),
	NAO_RESPONDIDO(4, "Não respondido");
	
	private int codigo;
	private String descricao;
	private RespostaQuestaoAnamnese(int codigo, String descricao) {
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
	
	public static RespostaQuestaoAnamnese toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (RespostaQuestaoAnamnese resp : RespostaQuestaoAnamnese.values()) {
			if (codigo.equals(resp.getCodigo())) {
				return resp;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

}

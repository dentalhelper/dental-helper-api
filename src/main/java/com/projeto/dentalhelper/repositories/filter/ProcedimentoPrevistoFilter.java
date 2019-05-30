package com.projeto.dentalhelper.repositories.filter;

public class ProcedimentoPrevistoFilter {
	private Long codigoPaciente;
	
	private Boolean finalizado;

	public Long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}
	
}

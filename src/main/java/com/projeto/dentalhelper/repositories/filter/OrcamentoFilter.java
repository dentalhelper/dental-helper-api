package com.projeto.dentalhelper.repositories.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrcamentoFilter {

	private Long codigoPaciente;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataOrcamentoDe;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataOrcamentoAte;

	public Long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public Date getDataOrcamentoDe() {
		return dataOrcamentoDe;
	}

	public void setDataOrcamentoDe(Date dataOrcamentoDe) {
		this.dataOrcamentoDe = dataOrcamentoDe;
	}

	public Date getDataOrcamentoAte() {
		return dataOrcamentoAte;
	}

	public void setDataOrcamentoAte(Date dataOrcamentoAte) {
		this.dataOrcamentoAte = dataOrcamentoAte;
	}
	
	

}

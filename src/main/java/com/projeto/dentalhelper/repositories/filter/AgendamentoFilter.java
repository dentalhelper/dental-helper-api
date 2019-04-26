package com.projeto.dentalhelper.repositories.filter;

import java.util.Date;

public class AgendamentoFilter {
	
	private Date dataAgendamento;
	
	private Date dataAgendamentoMin;
	
	private Date dataAgendamentoMax;
	
	private Date horaInicioMax;
	
	private Date horaInicioMin;
	
	private Long codPaciente;

	public Date getDataAgendamentoMin() {
		return dataAgendamentoMin;
	}

	public void setDataAgendamentoMin(Date dataAgendamentoMin) {
		this.dataAgendamentoMin = dataAgendamentoMin;
	}

	public Date getDataAgendamentoMax() {
		return dataAgendamentoMax;
	}

	public void setDataAgendamentoMax(Date dataAgendamentoMax) {
		this.dataAgendamentoMax = dataAgendamentoMax;
	}

	public Date getHoraInicioMax() {
		return horaInicioMax;
	}

	public void setHoraInicioMax(Date horaInicioMax) {
		this.horaInicioMax = horaInicioMax;
	}

	public Date getHoraInicioMin() {
		return horaInicioMin;
	}

	public void setHoraInicioMin(Date horaInicioMin) {
		this.horaInicioMin = horaInicioMin;
	}

	public Long getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(Long codPaciente) {
		this.codPaciente = codPaciente;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	
	

	
}

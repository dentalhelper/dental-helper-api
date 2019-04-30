package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class PacienteAgendamentoDTO extends ResourceSupport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long codigoPaciente;
	
	private List<AgendamentoResumoDTO> agendamentos;
	
	public PacienteAgendamentoDTO(List<AgendamentoResumoDTO> agendamentos, Long codigoPaciente) {
		this.agendamentos = agendamentos;
		this.codigoPaciente = codigoPaciente;
	}
	
	
	public Long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}


	public List<AgendamentoResumoDTO> getAgendamentos() {
		return agendamentos;
	}


	public void setAgendamentos(List<AgendamentoResumoDTO> agendamentos) {
		this.agendamentos = agendamentos;
	}



}

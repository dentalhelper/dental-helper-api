package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class PacienteAgendamentoDTO extends ResourceSupport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long codigoPaciente;
	
	private List<AgendamentoResumoPacienteDTO> agendamentos;
	
	public PacienteAgendamentoDTO(List<AgendamentoResumoPacienteDTO> agendamentos, Long codigoPaciente) {
		this.agendamentos = agendamentos;
		this.codigoPaciente = codigoPaciente;
	}
	
	
	public Long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}


	public List<AgendamentoResumoPacienteDTO> getAgendamentos() {
		return agendamentos;
	}


	public void setAgendamentos(List<AgendamentoResumoPacienteDTO> agendamentos) {
		this.agendamentos = agendamentos;
	}



}

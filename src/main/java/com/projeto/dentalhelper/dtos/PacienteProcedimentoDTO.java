package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class PacienteProcedimentoDTO extends ResourceSupport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long codigoPaciente;
	
	private List<ProcedimentoPrevistoResumoDTO> procedimentos;
	
	public PacienteProcedimentoDTO() {
		
	}
	
	public PacienteProcedimentoDTO(Long codigoPaciente, List<ProcedimentoPrevistoResumoDTO> procedimentos) {
		this.codigoPaciente = codigoPaciente;
		this.procedimentos = procedimentos;
	}
	

	public Long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public List<ProcedimentoPrevistoResumoDTO> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<ProcedimentoPrevistoResumoDTO> procedimentos) {
		this.procedimentos = procedimentos;
	}
	
	
	

}

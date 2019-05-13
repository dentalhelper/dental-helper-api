package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class PacienteOrcamentoDTO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigoPaciente;

	private List<OrcamentoResumoDTO> orcamentos;

	public PacienteOrcamentoDTO(Long codigoPaciente, List<OrcamentoResumoDTO> orcamentos) {
		this.codigoPaciente = codigoPaciente;
		this.orcamentos = orcamentos;
	}

	public Long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public List<OrcamentoResumoDTO> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<OrcamentoResumoDTO> orcamentos) {
		this.orcamentos = orcamentos;
	}

}

package com.projeto.dentalhelper.dtos;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.Anamnese;
import com.projeto.dentalhelper.domains.Paciente;

public class PacienteAnamneseDTO extends ResourceSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long codigoPaciente;
	
	private Anamnese anamnese;
	
	public PacienteAnamneseDTO(Paciente paciente) {
		super();
		this.codigoPaciente = paciente.getCodigo();
		this.anamnese = paciente.getAnamnese();
	}
	
	public Anamnese getAnamnese() {
		return anamnese;
	}

	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}

	public Long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}
	
	
	
	

}

package com.projeto.dentalhelper.builders;

import com.projeto.dentalhelper.domains.Paciente;

public class PacienteBuilder {
	
	private PacienteBuilder() {
		
	}
	
	
	public static Paciente novoPaciente() {

		return new Paciente();
	}

}
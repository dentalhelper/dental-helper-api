package com.projeto.dentalhelper.repositories.Paciente;

import java.util.List;

import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.repositories.filter.PacienteFilter;

public interface PacienteRepositoryQuery {
	
	public List<Paciente> buscarPorCpf(PacienteFilter Filter);
	
	public List<Paciente> buscarPorRg(PacienteFilter filter);
	
	public List<Paciente> filtrar(PacienteFilter filter);

}

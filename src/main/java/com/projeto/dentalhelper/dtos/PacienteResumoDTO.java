package com.projeto.dentalhelper.dtos;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.Paciente;

public class PacienteResumoDTO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;

	private String nome;

	private String cPF;

	private String telefone;

	private String email;

	public PacienteResumoDTO() {

	}

	public PacienteResumoDTO(Paciente paciente) {
		super();
		this.codigo = paciente.getCodigo();
		this.nome = paciente.getNome();
		this.cPF = paciente.getcPF();
		this.telefone = paciente.getTelefonePrincipal();
		this.email = paciente.getEmail();
		this.add(paciente.getLinks());
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getcPF() {
		return cPF;
	}

	public void setcPF(String cPF) {
		this.cPF = cPF;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

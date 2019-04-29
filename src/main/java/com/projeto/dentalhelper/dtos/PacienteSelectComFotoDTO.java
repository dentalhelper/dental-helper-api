package com.projeto.dentalhelper.dtos;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.Paciente;

public class PacienteSelectComFotoDTO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;

	private String nome;

	private String urlDaFoto;

	public PacienteSelectComFotoDTO() {

	}

	public PacienteSelectComFotoDTO(Paciente paciente) {
		super();
		this.codigo = paciente.getCodigo();
		this.nome = paciente.getNome();
		this.urlDaFoto = paciente.getUrlDaFoto();
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

	public String getUrlDaFoto() {
		return urlDaFoto;
	}

	public void setUrlDaFoto(String urlDaFoto) {
		this.urlDaFoto = urlDaFoto;
	}

}

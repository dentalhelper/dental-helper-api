package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import com.projeto.dentalhelper.domains.Paciente;

public class PacienteNovoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(max = 50)
	@NotBlank
	private String nome;

	@NotNull
	private Date dataNascimento;

	@Size(max = 30)
	@Pattern(regexp = "[0-9]{11}")
	@NotBlank
	private String cPF;

	@Size(max = 20)
	@Pattern(regexp = "[0-9]{7}")
	@NotBlank
	private String rG;

	private Integer estadoCivil;

	private Integer sexo;

	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	private String profissao;

	private String fotoPerfil;

	private String urlDaFoto;

	@NotBlank
	@Pattern(regexp = "[0-9]{11}")
	private String telefonePrincipal;

	
	@Pattern(regexp = "[0-9]{0,11}")
	private String telefone2;

	@NotBlank
	@Size(min = 3)
	private String logradouro;

	@NotNull
	private Integer numero;

	@NotBlank
	@Size(min = 3)
	private String bairro;
	
	@Pattern(regexp = "[0-9]{8}")
	@NotBlank
	private String CEP;

	private String complemento;

	@NotNull
	private Long codigoCidade;

	public PacienteNovoDTO() {

	}

	public PacienteNovoDTO(Paciente paciente) {
		super();
		this.nome = paciente.getNome();
		this.dataNascimento = paciente.getDataNascimento();
		this.cPF = paciente.getcPF();
		this.rG = paciente.getrG();
		this.estadoCivil = paciente.getEstadoCivil().getCodigo();
		this.sexo = paciente.getSexo().getCodigo();
		this.email = paciente.getEmail();
		this.profissao = paciente.getProfissao();
		this.fotoPerfil = paciente.getFotoPerfil();
		this.urlDaFoto = paciente.getUrlDaFoto();
		this.telefonePrincipal = paciente.getTelefonePrincipal();
		this.telefone2 = paciente.getTelefoneSecundario();
		this.logradouro = paciente.getEndereco().getLogradouro();
		this.numero = paciente.getEndereco().getNumero();
		this.bairro = paciente.getEndereco().getBairro();
		CEP = paciente.getEndereco().getCEP();
		this.complemento = paciente.getEndereco().getComplemento();
		this.codigoCidade = paciente.getEndereco().getCidade().getCodigo();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getcPF() {
		return cPF;
	}

	public void setcPF(String cPF) {
		this.cPF = cPF;
	}

	public String getrG() {
		return rG;
	}

	public void setrG(String rG) {
		this.rG = rG;
	}

	public Integer getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(Integer estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getUrlDaFoto() {
		return urlDaFoto;
	}

	public void setUrlDaFoto(String urlDaFoto) {
		this.urlDaFoto = urlDaFoto;
	}

	public String getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(String telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getCodigoCidade() {
		return codigoCidade;
	}

	public void setCodigoCidade(Long codigoCidade) {
		this.codigoCidade = codigoCidade;
	}

}

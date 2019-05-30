package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.projeto.dentalhelper.domains.Usuario;

public class UsuarioNovoDTO implements Serializable{

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
	@Pattern(regexp = "[0-9]{11}")
	private String telefonePrincipal;

	
	@Pattern(regexp = "[0-9]{0,11}")
	private String telefone2;

	@NotBlank
	@Size(min = 3)
	private String logradouro;

	@NotNull
	private String numero;

	@NotBlank
	@Size(min = 3)
	private String bairro;
	
	@Pattern(regexp = "[0-9]{8}")
	@NotBlank
	private String CEP;

	private String complemento;

	@NotNull
	private Long codigoCidade;
	
	@NotBlank
	@Size(min = 5)
	private String login;
	
	@NotBlank
	@Size(min = 5)
	private String senha;
	
	@NotNull
	private Integer tipo;
	
	

	public UsuarioNovoDTO() {
	}
	
	public UsuarioNovoDTO(Usuario u) {
		this.nome = u.getNome();
		this.dataNascimento = u.getDataNascimento();
		this.cPF = u.getcPF();
		this.rG = u.getrG();
		this.estadoCivil = u.getEstadoCivil().getCodigo();
		this.sexo = u.getSexo().getCodigo();
		this.email = u.getEmail();
		this.telefonePrincipal = u.getTelefonePrincipal();
		this.telefone2 = u.getTelefoneSecundario();
		this.logradouro = u.getEndereco().getLogradouro();
		this.numero = u.getEndereco().getNumero();
		this.bairro = u.getEndereco().getBairro();
		this.CEP = u.getEndereco().getCEP();
		this.complemento = u.getEndereco().getComplemento();
		this.codigoCidade = u.getEndereco().getCidade().getCodigo();
		this.login = u.getLogin();
		this.senha = u.getSenha();
		this.tipo = u.getTipo().getCodigo();
		
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}

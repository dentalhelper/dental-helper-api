package com.projeto.dentalhelper.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.dentalhelper.domains.enums.EstadoCivil;
import com.projeto.dentalhelper.domains.enums.Sexo;
import com.projeto.dentalhelper.domains.enums.TipoUsuario;

@Entity
@Table(name = "usuario")
@PrimaryKeyJoinColumn(name = "codigo")
public class Usuario extends Pessoa{

	private static final long serialVersionUID = 1L;
	
	private String login;
	
	@JsonIgnore
	private String senha;
	
	private Boolean ativo;
	
	private Integer tipo;
	
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	public Usuario() {
		
	}
	
	
	public Usuario(String nome, Date dataNascimento, String cPF, String rG, EstadoCivil estadoCivil, Sexo sexo,
			String email, String login, String senha, Boolean ativo, TipoUsuario tipo, Date dataCadastro) {
		super(nome, dataNascimento, cPF, rG, estadoCivil, sexo, email);
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.tipo = tipo.getCodigo();
		this.dataCadastro = dataCadastro;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public TipoUsuario getTipo() {
		return TipoUsuario.toEnum(tipo);
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo.getCodigo();
	}
	

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}

package com.projeto.dentalhelper.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projeto.dentalhelper.domains.enums.EstadoCivil;
import com.projeto.dentalhelper.domains.enums.Sexo;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa extends ObjetoIdentificado {

	private static final long serialVersionUID = 1L;

	@Size(max = 50)
	@NotBlank
	private String nome;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Size(max = 30)
	@NotBlank
	private String cPF;

	@Size(max = 20)
	@NotBlank
	private String rG;

	private Integer estadoCivil;
	
	private Integer sexo;

	@Size(max = 50)
	@Email
	private String email;

	@JsonIgnoreProperties("pessoa")
	@Valid
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Telefone> telefones = new ArrayList<Telefone>();

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	private Endereco endereco;

	public Pessoa() {
	}

	public Pessoa(String nome, Date dataNascimento, String cPF, String rG, EstadoCivil estadoCivil, Sexo sexo, String email) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cPF = cPF;
		this.rG = rG;
		this.estadoCivil = (estadoCivil == null) ? null : estadoCivil.getCodigo();
		this.sexo = (sexo == null) ? null : sexo.getCodigo();
		this.email = email;
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

	public Sexo getSexo() {
		return Sexo.toEnum(sexo);
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo.getCodigo();
	}

	public EstadoCivil getEstadoCivil() {
		return EstadoCivil.toEnum(estadoCivil);
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil.getCodigo();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefonePrincipal() {
		return this.telefones.size() > 0 ? this.telefones.get(0).getNumero() : "";
	}

}

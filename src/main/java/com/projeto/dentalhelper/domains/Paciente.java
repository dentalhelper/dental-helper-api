package com.projeto.dentalhelper.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projeto.dentalhelper.domains.enums.EstadoCivil;
import com.projeto.dentalhelper.domains.enums.Sexo;

@Entity
@Table(name = "paciente")
@PrimaryKeyJoinColumn(name = "codigo")
public class Paciente extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date dataCriacaoFicha;

	private String profissao;

	private String fotoPerfil;

	private String urlDaFoto;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_anamnese")
	private Anamnese anamnese;
	
	@Valid
	@JsonIgnoreProperties("paciente")
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Dente> dentes = new ArrayList<Dente>();
	
	
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "codigo_paciente")

	public Date getDataCriacaoFicha() {
		return dataCriacaoFicha;
	}

	public Paciente() {

	}

	public Paciente(String nome, Date dataNascimento, String cPF, String rG, EstadoCivil estadoCivil, Sexo sexo,
			String email, String profissao, String fotoPerfil, String urlDaFoto) {
		super(nome, dataNascimento, cPF, rG, estadoCivil, sexo, email);
		this.profissao = profissao;
		this.fotoPerfil = fotoPerfil;
		this.urlDaFoto = urlDaFoto;
	}

	public void setDataCriacaoFicha(Date dataCriacaoFicha) {
		this.dataCriacaoFicha = dataCriacaoFicha;
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

	public Anamnese getAnamnese() {
		return anamnese;
	}

	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}

	public List<Dente> getDentes() {
		return dentes;
	}

	public void setDentes(List<Dente> dentes) {
		this.dentes = dentes;
	}
	
	

}

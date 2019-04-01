package com.projeto.dentalhelper.domains;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.projeto.dentalhelper.domains.enums.CorDosDentes;
import com.projeto.dentalhelper.domains.enums.EscalaDentes;
import com.projeto.dentalhelper.domains.enums.FormaDoRosto;

@Entity
@Table (name = "paciente")
public class Paciente extends Pessoa{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataCriacaoFicha;
	
	@NotBlank
	private String profissao;
	
//	@Enumerated
//	private CorDosDentes corDosDentes;
//	@Enumerated
//	private FormaDoRosto formaDoRosto;
//	@Enumerated
//	private EscalaDentes escalaDentes;
	
	private String fotoPerfil;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private Anamnese anamnese;

	public Date getDataCriacaoFicha() {
		return dataCriacaoFicha;
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

//	public CorDosDentes getCorDosDentes() {
//		return corDosDentes;
//	}
//
//	public void setCorDosDentes(CorDosDentes corDosDentes) {
//		this.corDosDentes = corDosDentes;
//	}
//
//	public FormaDoRosto getFormaDoRosto() {
//		return formaDoRosto;
//	}
//
//	public void setFormaDoRosto(FormaDoRosto formaDoRosto) {
//		this.formaDoRosto = formaDoRosto;
//	}
//
//	public EscalaDentes getEscalaDentes() {
//		return escalaDentes;
//	}
//
//	public void setEscalaDentes(EscalaDentes escalaDentes) {
//		this.escalaDentes = escalaDentes;
//	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public Anamnese getAnamnese() {
		return anamnese;
	}

	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}
	

}

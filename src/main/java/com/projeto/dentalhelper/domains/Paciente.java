package com.projeto.dentalhelper.domains;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.projeto.dentalhelper.domains.enums.FormaDoRosto;

@Entity
@Table (name = "paciente")
@PrimaryKeyJoinColumn(name="codigo")
public class Paciente extends Pessoa{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Temporal(TemporalType.DATE)
	private Date dataCriacaoFicha;
	
	@NotBlank
	private String profissao;
	
	
	
	private String fotoPerfil;
	
//	@OneToOne(cascade = CascadeType.REMOVE)
//	private Anamnese anamnese;

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

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

//	public Anamnese getAnamnese() {
//		return anamnese;
//	}
//
//	public void setAnamnese(Anamnese anamnese) {
//		this.anamnese = anamnese;
//	}
	
	
	

}

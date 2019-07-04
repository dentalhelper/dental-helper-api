package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;

public class ProcedimentoPrevistoResumoDTO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;

	private Float valorDoProcedimento;

	private Boolean finalizado;

	private Date dataInicio;

	private Date dataFinalizacao;

	private Long codProcedimento;

	private String nomeProcedimento;

	private String faceDente;

	private Integer dente;

	public ProcedimentoPrevistoResumoDTO(ProcedimentoPrevisto p) {
		this.codigo = p.getCodigo();
		this.valorDoProcedimento = p.getValorDoProcedimento();
		this.finalizado = p.getFinalizado();
		this.dataFinalizacao = p.getDataFinalizacao();
		this.dataInicio = p.getDataInicio();
		this.codProcedimento = p.getProcedimento().getCodigo();
		this.nomeProcedimento = p.getProcedimento().getNome();
		this.faceDente = p.getFaceDente().getDescricao();
		if (!p.getDentes().isEmpty()) {
			this.dente = p.getDentes().get(0).getNumero();
		}
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Float getValorDoProcedimento() {
		return valorDoProcedimento;
	}

	public void setValorDoProcedimento(Float valorDoProcedimento) {
		this.valorDoProcedimento = valorDoProcedimento;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public Long getCodProcedimento() {
		return codProcedimento;
	}

	public void setCodProcedimento(Long codProcedimento) {
		this.codProcedimento = codProcedimento;
	}

	public String getNomeProcedimento() {
		return nomeProcedimento;
	}

	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}

	public String getFaceDente() {
		return faceDente;
	}

	public void setFaceDente(String faceDente) {
		this.faceDente = faceDente;
	}

	public Integer getDente() {
		return dente;
	}

	public void setDente(Integer dente) {
		this.dente = dente;
	}

}

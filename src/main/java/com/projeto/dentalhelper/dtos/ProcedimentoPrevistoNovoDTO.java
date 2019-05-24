package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;

public class ProcedimentoPrevistoNovoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long codigo;
	
	private Float valor;
	
	private Boolean finalizado;
	
	private Date dataInicio;
	
	private Date dataFinalizacao;
	

	public ProcedimentoPrevistoNovoDTO() {
		super();
	}
	
	public ProcedimentoPrevistoNovoDTO(ProcedimentoPrevisto p) {
		super();
		this.codigo = p.getProcedimento().getCodigo();
		this.valor = p.getValorDoProcedimento();
		this.finalizado = p.getFinalizado();
		this.dataInicio = p.getDataInicio();
		this.dataFinalizacao = p.getDataFinalizacao();
	}
	

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
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

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}

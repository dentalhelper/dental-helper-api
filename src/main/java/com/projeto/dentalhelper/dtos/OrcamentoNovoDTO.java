package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.domains.Procedimento;

public class OrcamentoNovoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Float valorTotal;
	
	
	private Boolean aprovado;
	
	private List<Procedimento> procedimentos = new ArrayList<Procedimento>();
	
	@NotNull
	private Long codPaciente;
	
	public OrcamentoNovoDTO () {
		
	}
	
	public OrcamentoNovoDTO(Orcamento o) {
		this.valorTotal = o.getValorTotal();
		this.aprovado = o.getAprovado();
		this.procedimentos = o.getProcedimentos();
		this.codPaciente = o.getPaciente().getCodigo();
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Long getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(Long codPaciente) {
		this.codPaciente = codPaciente;
	}
	
	
	

}

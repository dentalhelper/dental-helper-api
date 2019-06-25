package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.Dente;
import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;
import com.projeto.dentalhelper.domains.enums.StatusDenteProcedimento;

public class DenteOdontogramaResumoDTO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	
	private Integer numero;
	
	private String observacao;
	
	private Boolean existente;
	
	private Integer status;
	
	private List<ProcedimentoOdontogramaResumoDTO> procedimentos = new ArrayList<ProcedimentoOdontogramaResumoDTO>();
	
	
	
	

	public DenteOdontogramaResumoDTO() {
	}
	
	public DenteOdontogramaResumoDTO(Dente d) {
		this.codigo = d.getCodigo();
		this.numero = d.getNumero();
		this.observacao = d.getObservacao();
		this.existente = d.getExistente();
		this.status = adicionarStatus(d.getProcedimentosPrevistos()).getCodigo();
		this.procedimentos = adicionarNomesProcedimentos(d.getProcedimentosPrevistos());
	}
	

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getExistente() {
		return existente;
	}

	public void setExistente(Boolean existente) {
		this.existente = existente;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<ProcedimentoOdontogramaResumoDTO> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<ProcedimentoOdontogramaResumoDTO> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public StatusDenteProcedimento adicionarStatus(List<ProcedimentoPrevisto> procedimentos) {
		if(procedimentos == null || procedimentos.size() == 0) {
			return StatusDenteProcedimento.SEM_TRATAMENTOS;
		}
		for(ProcedimentoPrevisto p: procedimentos) {
			if(p.getFinalizado() == null || p.getFinalizado() == false) {
				return StatusDenteProcedimento.EM_ANDAMENTO;
			}
		}
		return StatusDenteProcedimento.FINALIZADO;
	}
	
	public List<ProcedimentoOdontogramaResumoDTO> adicionarNomesProcedimentos(List<ProcedimentoPrevisto> procedimentos){
		List<ProcedimentoOdontogramaResumoDTO> procedimentosResumo = new ArrayList<ProcedimentoOdontogramaResumoDTO>();
		for(ProcedimentoPrevisto p: procedimentos) {
			procedimentosResumo.add(new ProcedimentoOdontogramaResumoDTO(p.getProcedimento().getNome(), p.getFinalizado()));
		}
		
		return procedimentosResumo;
	}

}

package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;

public class OrcamentoPagamentoDTO extends ResourceSupport implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long codigoOrcamento;
	
	private Date dataPagamento;
	
	private String tratamentos;
	
	private Float valorEmAberto;
	
	private Float valorPago;
	
	private Integer statusPagamento;
	
	public OrcamentoPagamentoDTO(Orcamento o) {
		super();
		this.codigoOrcamento = o.getCodigo();
		this.dataPagamento = pegarUltimaDataDePagamento(o.getPagamentos());
		this.tratamentos = pegarNomesDeTratamentos(o.getProcedimentosPrevistos());
		this.valorPago = valorPago(o);
		this.valorEmAberto = o.getValorTotal()-valorPago(o);
		this.statusPagamento = o.getStatus().getCodigo();
	}

	public OrcamentoPagamentoDTO() {}
	
	
	



	public Long getCodigoOrcamento() {
		return codigoOrcamento;
	}

	public void setCodigoOrcamento(Long codigoOrcamento) {
		this.codigoOrcamento = codigoOrcamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getTratamentos() {
		return tratamentos;
	}

	public void setTratamentos(String tratamentos) {
		this.tratamentos = tratamentos;
	}

	public Float getValorEmAberto() {
		return valorEmAberto;
	}

	public void setValorEmAberto(Float valorEmAberto) {
		this.valorEmAberto = valorEmAberto;
	}

	public Float getValorPago() {
		return valorPago;
	}

	public void setValorPago(Float valorPago) {
		this.valorPago = valorPago;
	}

	public Integer getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(Integer statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
	
	
	public Date pegarUltimaDataDePagamento(List<Pagamento> pagamentos) {
		if(pagamentos.size() == 0) {
			return null;
		}
		
		return pagamentos.get(pagamentos.size()-1).getDataPagamento();
		
	}
	
	public String pegarNomesDeTratamentos(List<ProcedimentoPrevisto> procedimentos) {
		String resultado = "";
		for(int i = 0; i<procedimentos.size();i++) {
			resultado += procedimentos.get(i).getProcedimento().getNome();
			if(i<procedimentos.size()-1) {
				resultado += ", ";
			}
		}		
		return resultado;		
	}
	
	public Float valorPago(Orcamento o) {
		if(o.getPagamentos().size() ==0) {
			return new Float(0);
		}
		Float valorPago = new Float(0);
		for(Pagamento p: o.getPagamentos()) {
			valorPago += p.getValor();
		}
		return valorPago;
	}
}

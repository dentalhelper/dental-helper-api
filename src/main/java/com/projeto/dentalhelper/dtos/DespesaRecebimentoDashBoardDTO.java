package com.projeto.dentalhelper.dtos;

import java.io.Serializable;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.domains.enums.TipoPagamento;

public class DespesaRecebimentoDashBoardDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer tipo;
	
	private Float valor;
	

	private String descricao;
	
	private Long idRecebimento;
	
	private Long idDespesa;
	
	public DespesaRecebimentoDashBoardDTO(Pagamento p) {
		this.tipo = p.getTipo().getCodigo();
		this.valor = p.getValor();
		if(p.getTipo() == TipoPagamento.RECEBIMENTO) {
			this.descricao = adicionarDescricaoComBaseNoOrcamento(p.getOrcamento());
			this.idRecebimento = p.getOrcamento().getPaciente().getCodigo();
		}
	}
	
	
	
	public DespesaRecebimentoDashBoardDTO(Despesa d) {
		this.tipo = d.getPagamento().getTipo().getCodigo();
		this.valor = d.getPagamento().getValor();
		this.descricao = d.getDescricao();
		this.idDespesa = d.getCodigo();
	}
	
	

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdRecebimento() {
		return idRecebimento;
	}

	public void setIdRecebimento(Long idRecebimento) {
		this.idRecebimento = idRecebimento;
	}

	public Long getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(Long idDespesa) {
		this.idDespesa = idDespesa;
	}
	
	private String adicionarDescricaoComBaseNoOrcamento(Orcamento o) {
		String resultado = "";
		for(int i = 0; i<o.getProcedimentosPrevistos().size(); i++) {
			if(i<o.getProcedimentosPrevistos().size()) {
				resultado += ", ";
			}
			resultado += o.getProcedimentosPrevistos().get(i).getProcedimento().getNome();
		}
		
		return resultado;
	}
	
	
	

}

package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.Date;

public class RecebimentoDespesaDashBoardGraficoDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;

	private Date dataSemana;
	
	private Float recebimento;
	
	private Float despesa;
	



	public Date getDataSemana() {
		return dataSemana;
	}

	public void setDataSemana(Date dataSemana) {
		this.dataSemana = dataSemana;
	}

	public Float getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Float recebimento) {
		this.recebimento = recebimento;
	}

	public Float getDespesa() {
		return despesa;
	}

	public void setDespesa(Float despesa) {
		this.despesa = despesa;
	}
	
	
	
	

}

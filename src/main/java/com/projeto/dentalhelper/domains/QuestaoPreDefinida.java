package com.projeto.dentalhelper.domains;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "questao_pre_definida")
public class QuestaoPreDefinida extends ObjetoIdentificado{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String questao;

	public String getQuestao() {
		return questao;
	}

	public void setQuestao(String questao) {
		this.questao = questao;
	}
	
	

}

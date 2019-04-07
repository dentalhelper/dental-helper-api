package com.projeto.dentalhelper.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.projeto.dentalhelper.domains.enums.RespostaQuestaoAnamnese;

@Entity
@Table(name = "questao")
public class Questao extends ObjetoIdentificado{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String descricao;
	

	private Integer resposta;
	
	@Column(name = "inform_adicionais")
	private String informAdicionais;
	
	@ManyToOne
	@JoinColumn(name = "codigo_anamnese")
	private Anamnese anamnese;

	public String getDescricao() {
		return descricao;
	}

	public Anamnese getAnamnese() {
		return anamnese;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}

	public RespostaQuestaoAnamnese getResposta() {
		return RespostaQuestaoAnamnese.toEnum(resposta);
	}

	public void setResposta(RespostaQuestaoAnamnese resposta) {
		this.resposta = resposta.getCodigo();
	}

	public String getInformAdicionais() {
		return informAdicionais;
	}

	public void setInformAdicionais(String informAdicionais) {
		this.informAdicionais = informAdicionais;
	}
	
	
	
	
	
	
	
	

	

}

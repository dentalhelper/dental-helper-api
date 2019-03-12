package com.projeto.dentalhelper.builders;

import com.projeto.dentalhelper.domains.CategoriaDespesa;

public class CategoriaDespesaBuilder {

	private CategoriaDespesa categoriaDespesa;

	private CategoriaDespesaBuilder() {

	}

	public static CategoriaDespesaBuilder umaCategoriaDespesa() {
		CategoriaDespesaBuilder builder = new CategoriaDespesaBuilder();
		builder.categoriaDespesa = new CategoriaDespesa();
		builder.categoriaDespesa.setNome("Combustível");
		return builder;
	}

	public CategoriaDespesaBuilder comNome(String nome) {
		categoriaDespesa.setNome(nome);
		return this;
	}
	
	public CategoriaDespesaBuilder semNome() {
		categoriaDespesa.setNome(null);
		return this;
	}

	public CategoriaDespesa agora() {
		return categoriaDespesa;
	}
}

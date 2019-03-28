package com.projeto.dentalhelper.builders;

import com.projeto.dentalhelper.domains.Despesa;

public class DespesaBuilder {
	

	
	private DespesaBuilder() {
		
	}
	
	
	public static Despesa novaDespesa() {

		return new Despesa();
	}


}

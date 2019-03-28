package com.projeto.dentalhelper.builders;

import com.projeto.dentalhelper.domains.Procedimento;

public class ProcedimentoBuilder {
	private ProcedimentoBuilder() {
		
	}
	
	public static Procedimento novoProcedimento() {

		return new Procedimento();
	}

}

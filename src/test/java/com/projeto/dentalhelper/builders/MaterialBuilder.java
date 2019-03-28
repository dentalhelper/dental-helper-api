package com.projeto.dentalhelper.builders;

import com.projeto.dentalhelper.domains.Material;

public class MaterialBuilder {

	private MaterialBuilder() {
		
	}
	
	public static Material novoMaterial() {

		return new Material();
	}
}

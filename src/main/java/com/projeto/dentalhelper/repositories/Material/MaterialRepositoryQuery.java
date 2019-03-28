package com.projeto.dentalhelper.repositories.Material;

import java.util.List;

import com.projeto.dentalhelper.domains.Material;
import com.projeto.dentalhelper.repositories.filter.MaterialFilter;

public interface MaterialRepositoryQuery {
	
	public List<Material> filtrar(MaterialFilter filter);

}

package com.projeto.dentalhelper.repositories.ProcedimentoPrevisto;

import java.util.List;

import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;
import com.projeto.dentalhelper.repositories.filter.ProcedimentoPrevistoFilter;

public interface ProcedimentoPrevistoRepositoryQuery {
	public List<ProcedimentoPrevisto> filtrar(ProcedimentoPrevistoFilter filter);
}

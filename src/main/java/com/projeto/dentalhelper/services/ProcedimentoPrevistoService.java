package com.projeto.dentalhelper.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;
import com.projeto.dentalhelper.repositories.ProcedimentoPrevistoRepository;
import com.projeto.dentalhelper.repositories.filter.ProcedimentoPrevistoFilter;

@Service
public class ProcedimentoPrevistoService extends AbstractService<ProcedimentoPrevisto, ProcedimentoPrevistoRepository>{

	public List<ProcedimentoPrevisto> filtar (ProcedimentoPrevistoFilter filter){
		return repository.filtrar(filter);
	}
	
}

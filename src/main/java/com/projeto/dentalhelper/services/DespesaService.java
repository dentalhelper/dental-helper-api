package com.projeto.dentalhelper.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.repositories.DespesaRepository;
import com.projeto.dentalhelper.repositories.filter.DespesaFilter;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class DespesaService extends AbstractService<Despesa, DespesaRepository>{
	
	@Autowired
	private PagamentoService pagamentoService;
	
	public List<Despesa> filtrar(DespesaFilter filter){
		
		return repository.filtrar(filter);
		
	}
	
	
	@Override
	public Despesa salvar(Despesa objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
		if(objeto.getDescricao() == null || objeto.getDescricao() == "") {
			objeto.setDescricao(objeto.getCategoria().getNome());
		}
		objeto.getPagamento().setValor(objeto.getValor());
		return repository.save(objeto);
	}
	
	

}

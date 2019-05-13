package com.projeto.dentalhelper.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.repositories.OrcamentoRepository;
import com.projeto.dentalhelper.repositories.filter.OrcamentoFilter;
import com.projeto.dentalhelper.services.exceptions.OrcamentoDeveConterProcedimentoException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class OrcamentoService extends AbstractService<Orcamento, OrcamentoRepository>{
	
	
	@Autowired
	private ProcedimentoService procedimentoService;

	@Autowired
	private PacienteService pacienteService;

	@Override
	public Orcamento salvar(Orcamento objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
		
		if(objeto.getProcedimentos().isEmpty()) {
			throw new OrcamentoDeveConterProcedimentoException("O orçamento deve conter pelo menos 1 procedimento, tamanho da lista :"+ objeto.getProcedimentos().size());
		}

		objeto.setProcedimentos(buscarProcedimentos(objeto));
		objeto.setPaciente(pacienteService.buscarPorCodigo(objeto.getPaciente().getCodigo()));
		
		
		objeto.setAprovado(false);
		float valorTotal = 0;
		for(Procedimento p: objeto.getProcedimentos()) {
			valorTotal += p.getValorBase();
		}
		objeto.setValorTotal(valorTotal);
		
		
		return repository.save(objeto);
	}
	
	@Override
	public Orcamento atualizar(Long codigo, Orcamento objetoModificado) throws ServiceApplicationException{
		Orcamento objetoAtualizado = buscarPorCodigo(codigo);
		
		if(objetoModificado.getProcedimentos().isEmpty()) {
			throw new OrcamentoDeveConterProcedimentoException("O orçamento deve conter pelo menos 1 procedimento, tamanho da lista :"+ objetoModificado.getProcedimentos().size());
		}
		
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo");
		return repository.save(objetoAtualizado);
	}
	
	
	public List<Procedimento> buscarProcedimentos (Orcamento o){
		List<Procedimento> procedimentos = new ArrayList<Procedimento>();
		for(Procedimento p: o.getProcedimentos()) {
			procedimentos.add(procedimentoService.buscarPorCodigo(p.getCodigo()));
		}
		return procedimentos;
	}
	
	public List<Orcamento> filtrar (OrcamentoFilter filter){
		return repository.filtrar(filter);
	}
	
	
}

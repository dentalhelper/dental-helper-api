package com.projeto.dentalhelper.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.dtos.OrcamentoNovoDTO;
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
		
		objeto.setAprovado(false);
		if(objeto.getValorTotal() == null) {
			float valorTotal = 0;
			for(Procedimento p: objeto.getProcedimentos()) {
				valorTotal += p.getValorBase();
			}
			objeto.setValorTotal(valorTotal);
		}
			
		
		return repository.save(objeto);
	}
	
	@Override
	public Orcamento atualizar(Long codigo, Orcamento objetoModificado) throws ServiceApplicationException{
		Orcamento objetoAtualizado = buscarPorCodigo(codigo);
		
		if(objetoModificado.getValorTotal() == null) {
			objetoModificado.setValorTotal(objetoAtualizado.getValorTotal());
		}
		if(objetoModificado.getAprovado() == null) {
			objetoModificado.setAprovado(objetoAtualizado.getAprovado());
		}
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo", "pagamentos");
		return repository.save(objetoAtualizado);
	}
	
	
	public List<Procedimento> buscarProcedimentos (List<Procedimento> pro){
		List<Procedimento> procedimentos = new ArrayList<Procedimento>();
		for(Procedimento p: pro) {
			procedimentos.add(procedimentoService.buscarPorCodigo(p.getCodigo()));
		}
		return procedimentos;
	}
	
	public List<Orcamento> filtrar (OrcamentoFilter filter){
		return repository.filtrar(filter);
	}
	
	public Orcamento fromDTO(OrcamentoNovoDTO objetoDTO) throws OrcamentoDeveConterProcedimentoException {
		
		procedimentosVazio(objetoDTO.getProcedimentos());
		
		List<Procedimento> procedimentos = buscarProcedimentos(objetoDTO.getProcedimentos());
		Paciente paciente = pacienteService.buscarPorCodigo(objetoDTO.getCodPaciente());
		
		Orcamento orcamento = new Orcamento(objetoDTO.getValorTotal(), objetoDTO.getDataOrcamento(), objetoDTO.getAprovado(), procedimentos, paciente, null);
		
		return orcamento;
	}
	
	public boolean procedimentosVazio (List<Procedimento> procedimentos) throws OrcamentoDeveConterProcedimentoException {
		if(procedimentos.isEmpty()) {
			throw new OrcamentoDeveConterProcedimentoException("O orçamento deve conter pelo menos 1 procedimento, tamanho da lista :"+ procedimentos.size());
		}
		return false;
	}
	
	
}

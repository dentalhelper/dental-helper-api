package com.projeto.dentalhelper.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;
import com.projeto.dentalhelper.dtos.OrcamentoNovoDTO;
import com.projeto.dentalhelper.dtos.ProcedimentoPrevistoNovoDTO;
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
			for(ProcedimentoPrevisto p: objeto.getProcedimentosPrevistos()) {
				valorTotal += p.getValorDoProcedimento();
			}
			objeto.setValorTotal(valorTotal);
		}
		
		for(ProcedimentoPrevisto p: objeto.getProcedimentosPrevistos()) {
			p.setOrcamento(objeto);
			p.setFinalizado(false);
			p.setDataInicio(null);
			p.setDataFinalizacao(null);
		}
		
		
		Calendar calendar = new GregorianCalendar();
		objeto.setDataOrcamento(calendar.getTime());
			
		
		return repository.save(objeto);
	}
	
	@Override
	public Orcamento atualizar(Long codigo, Orcamento objetoModificado) throws ServiceApplicationException{
		Orcamento objetoAtualizado = buscarPorCodigo(codigo);

		int tam = objetoAtualizado.getProcedimentosPrevistos().size() % objetoModificado.getProcedimentosPrevistos().size();
		for(int i = 0; i<tam; i++) {
			objetoModificado.getProcedimentosPrevistos().get(i).setCodigo(objetoAtualizado.getProcedimentosPrevistos().get(i).getCodigo());
		}
		
		if(objetoModificado.getValorTotal() == null) {
			objetoModificado.setValorTotal(objetoAtualizado.getValorTotal());
		}
		if(objetoModificado.getAprovado() == null) {
			objetoModificado.setAprovado(objetoAtualizado.getAprovado());
		}
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo", "pagamentos", "dataOrcamento");
		return repository.save(objetoAtualizado);
	}
	
	
	public Procedimento buscarProcedimento (Long codigoProcedimento){
		return procedimentoService.buscarPorCodigo(codigoProcedimento);
	}
	
	public List<Orcamento> filtrar (OrcamentoFilter filter){
		return repository.filtrar(filter);
	}
	
	public Orcamento fromDTO(OrcamentoNovoDTO objetoDTO) throws OrcamentoDeveConterProcedimentoException {
		
		procedimentosVazio(objetoDTO.getProcedimentos());
		
		List<ProcedimentoPrevisto> procedimentosPrevistos = new ArrayList<ProcedimentoPrevisto>();
		
		for(ProcedimentoPrevistoNovoDTO pp: objetoDTO.getProcedimentos()) {
			procedimentosPrevistos.add(procedimentoPrevistoFromDTO(pp));
		}
		
		Paciente paciente = pacienteService.buscarPorCodigo(objetoDTO.getCodPaciente());
		
		Orcamento orcamento = new Orcamento(objetoDTO.getValorTotal(), objetoDTO.getAprovado(), procedimentosPrevistos, paciente, null);
		
		return orcamento;
	}
	
	
	public ProcedimentoPrevisto procedimentoPrevistoFromDTO (ProcedimentoPrevistoNovoDTO objetoDTO) {
		
		Procedimento procedimento = procedimentoService.buscarPorCodigo(objetoDTO.getCodProcedimento());
		
		if(objetoDTO.getValor() == null) {
			objetoDTO.setValor(procedimento.getValorBase());
		}
		
		
		ProcedimentoPrevisto procedimentoPrevisto = new ProcedimentoPrevisto(objetoDTO.getValor(), objetoDTO.getFinalizado(), objetoDTO.getDataInicio(), 
				objetoDTO.getDataFinalizacao(), procedimento, null);
		
		return procedimentoPrevisto;
		
	}
	
	public boolean procedimentosVazio (List<ProcedimentoPrevistoNovoDTO> procedimentos) throws OrcamentoDeveConterProcedimentoException {
		if(procedimentos.isEmpty()) {
			throw new OrcamentoDeveConterProcedimentoException("O orçamento deve conter pelo menos 1 procedimento, tamanho da lista :"+ procedimentos.size());
		}
		return false;
	}
	
	
}

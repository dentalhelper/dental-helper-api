package com.projeto.dentalhelper.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Dente;
import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;
import com.projeto.dentalhelper.domains.enums.StatusPagamento;
import com.projeto.dentalhelper.dtos.OrcamentoNovoDTO;
import com.projeto.dentalhelper.dtos.ProcedimentoPrevistoNovoDTO;
import com.projeto.dentalhelper.repositories.OrcamentoRepository;
import com.projeto.dentalhelper.repositories.filter.OrcamentoFilter;
import com.projeto.dentalhelper.services.exceptions.DenteInvalidoDePacienteException;
import com.projeto.dentalhelper.services.exceptions.OrcamentoDeveConterProcedimentoException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class OrcamentoService extends AbstractService<Orcamento, OrcamentoRepository>{
	
	
	@Autowired
	private ProcedimentoService procedimentoService;

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private DenteService denteService;

	@Override
	public Orcamento salvar(Orcamento objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);	
		
		if(objeto.getAprovado() == null) {
			objeto.setAprovado(false);
		}
		objeto.setStatus(StatusPagamento.ABERTO);
		for(ProcedimentoPrevisto p: objeto.getProcedimentosPrevistos()) {
			p.setOrcamento(objeto);
			p.setFinalizado(false);
			p.setDataInicio(null);
			p.setDataFinalizacao(null);
			if(p.getValorDoProcedimento() == null) {
				p.setValorDoProcedimento(p.getProcedimento().getValorBase());
			}
		}
		
		if(objeto.getValorTotal() == null) {
			float valorTotal = 0;
			for(ProcedimentoPrevisto p: objeto.getProcedimentosPrevistos()) {
				valorTotal += p.getValorDoProcedimento();
			}
			objeto.setValorTotal(valorTotal);
		}
		if(objeto.getDesconto() == null) {
			objeto.setDesconto(new Float(0));
		}
		
		
		
		Calendar calendar = new GregorianCalendar();
		objeto.setDataOrcamento(calendar.getTime());
			
		
		return repository.save(objeto);
	}
	
	@Override
	public Orcamento atualizar(Long codigo, Orcamento objetoModificado) throws ServiceApplicationException{
		Orcamento objetoAtualizado = buscarPorCodigo(codigo);
		
		int tam = 0;
		int tamDiferenca = 0;
		int tamModificado = objetoModificado.getProcedimentosPrevistos().size();
		int tamAtualizado = objetoAtualizado.getProcedimentosPrevistos().size();
		if(tamModificado > tamAtualizado){
			tam = tamAtualizado;
			tamDiferenca = tamModificado - tamAtualizado;
		}
		else if(tamModificado < tamAtualizado){
			tam = tamModificado;
		}
		else {
			tam = tamAtualizado;
		}
		for(int i = 0; i<tam; i++) {
			ProcedimentoPrevisto procedimentoPrevisto = objetoModificado.getProcedimentosPrevistos().get(i);
			ProcedimentoPrevisto procedimentoAtualizado = objetoAtualizado.getProcedimentosPrevistos().get(i);
			procedimentoPrevisto.setCodigo(procedimentoAtualizado.getCodigo());
			if(procedimentoPrevisto.getFinalizado() == null) {
				procedimentoPrevisto.setFinalizado(procedimentoAtualizado.getFinalizado());
			}
			if(procedimentoPrevisto.getValorDoProcedimento() == null) {
				procedimentoPrevisto.setValorDoProcedimento(procedimentoAtualizado.getValorDoProcedimento());
			}
			if(procedimentoPrevisto.getDataInicio() == null) {
				procedimentoPrevisto.setDataInicio(procedimentoAtualizado.getDataInicio());
			}
			if(procedimentoPrevisto.getDataFinalizacao() == null) {
				procedimentoPrevisto.setDataFinalizacao(procedimentoAtualizado.getDataFinalizacao());
			}
		}
		
		for(int i = tam; i < tam+tamDiferenca; i++) {
			ProcedimentoPrevisto procedimentoPrevisto = objetoModificado.getProcedimentosPrevistos().get(i);
			procedimentoPrevisto.setFinalizado(false);
			procedimentoPrevisto.setDataInicio(null);
			procedimentoPrevisto.setDataFinalizacao(null);
			if(procedimentoPrevisto.getValorDoProcedimento() == null) {
				procedimentoPrevisto.setValorDoProcedimento(procedimentoPrevisto.getProcedimento().getValorBase());
			}
		}

		if(objetoModificado.getValorTotal() == null) {
			objetoModificado.setValorTotal(objetoAtualizado.getValorTotal());
		}
		if(objetoModificado.getAprovado() == null) {
			objetoModificado.setAprovado(objetoAtualizado.getAprovado());
		}
		if(objetoModificado.getDesconto() == null) {
			objetoModificado.setDesconto(objetoAtualizado.getDesconto());
		}
		
		objetoAtualizado.getProcedimentosPrevistos().clear();
		objetoAtualizado.getProcedimentosPrevistos().addAll(objetoModificado.getProcedimentosPrevistos());

		objetoAtualizado.getProcedimentosPrevistos().forEach(procedimento -> procedimento.setOrcamento(objetoAtualizado));
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo", "pagamentos", "dataOrcamento", "procedimentosPrevistos", "status", "paciente");
		return repository.save(objetoAtualizado);
	}
	
	
	public Procedimento buscarProcedimento (Long codigoProcedimento){
		return procedimentoService.buscarPorCodigo(codigoProcedimento);
	}
	
	public List<Orcamento> filtrar (OrcamentoFilter filter){
		return repository.filtrar(filter);
	}
	
	public Orcamento fromDTO(OrcamentoNovoDTO objetoDTO) throws OrcamentoDeveConterProcedimentoException, DenteInvalidoDePacienteException {

		procedimentosVazio(objetoDTO.getProcedimentos());
		
		Paciente paciente = pacienteService.buscarPorCodigo(objetoDTO.getCodPaciente());
		
		List<ProcedimentoPrevisto> procedimentosPrevistos = new ArrayList<ProcedimentoPrevisto>();
		
		for(ProcedimentoPrevistoNovoDTO pp: objetoDTO.getProcedimentos()) {
			procedimentosPrevistos.add(procedimentoPrevistoFromDTO(pp, paciente.getCodigo()));
		}
		
		
		
		
		Orcamento orcamento = new Orcamento(objetoDTO.getValorTotal(), objetoDTO.getAprovado(), procedimentosPrevistos, paciente, null, objetoDTO.getDesconto());
		
		return orcamento;
	}
	
	
	public ProcedimentoPrevisto procedimentoPrevistoFromDTO (ProcedimentoPrevistoNovoDTO objetoDTO, Long codPaciente) throws DenteInvalidoDePacienteException {
		
		Procedimento procedimento = procedimentoService.buscarPorCodigo(objetoDTO.getCodigo());
		
		List<Dente> dentes = new ArrayList<Dente>();
		
		for(Long codigo: objetoDTO.getCodDentes()) {
			Dente dente = denteService.buscarPorCodigo(codigo);
			if(dente.getPaciente().getCodigo() != codPaciente || dente.getExistente() == false) {
				throw new DenteInvalidoDePacienteException("Dente de paciente inválido");
			}
			
			if(!dentes.contains(dente)) {
				dentes.add(dente);
			}
			
		}
		
		if(objetoDTO.getValor() == null) {
			objetoDTO.setValor(procedimento.getValorBase());
		}
		
		
		ProcedimentoPrevisto procedimentoPrevisto = new ProcedimentoPrevisto(objetoDTO.getValor(), objetoDTO.getFinalizado(), objetoDTO.getDataInicio(), 
				objetoDTO.getDataFinalizacao(), procedimento, null, dentes);
		
		return procedimentoPrevisto;
		
	}
	
	public boolean procedimentosVazio (List<ProcedimentoPrevistoNovoDTO> procedimentos) throws OrcamentoDeveConterProcedimentoException {
		if(procedimentos.isEmpty()) {
			throw new OrcamentoDeveConterProcedimentoException("O orçamento deve conter pelo menos 1 procedimento, tamanho da lista :"+ procedimentos.size());
		}
		return false;
	}
	
	public Orcamento atualizarStatus(Long codigo, String aprovado) {
		Orcamento orcamento = buscarPorCodigo(codigo);
		orcamento.setAprovado(true);
		
		return repository.save(orcamento);
	}
	
	public Orcamento atualizarStatusPagamento(Long codigo, Integer status) {
		Orcamento orcamento = buscarPorCodigo(codigo);
		orcamento.setStatus(StatusPagamento.toEnum(status));
		
		return repository.save(orcamento);
	}
	
}

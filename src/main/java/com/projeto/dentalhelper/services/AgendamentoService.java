package com.projeto.dentalhelper.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.domains.enums.StatusAgendamento;
import com.projeto.dentalhelper.dtos.AgendamentoNovoDTO;
import com.projeto.dentalhelper.repositories.AgendamentoRepository;
import com.projeto.dentalhelper.repositories.filter.AgendamentoFilter;
import com.projeto.dentalhelper.services.exceptions.AgendamentoJaCadastradoNoHorarioException;
import com.projeto.dentalhelper.services.exceptions.DadoInvalidoException;
import com.projeto.dentalhelper.services.exceptions.DataAgendamentoInvalidaException;
import com.projeto.dentalhelper.services.exceptions.HoraAgendamentoInvalidaException;
import com.projeto.dentalhelper.services.exceptions.OrcamentoNaoAprovadoException;
import com.projeto.dentalhelper.services.exceptions.ProcedimentoNaoEstaEmOrcamentoException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class AgendamentoService extends AbstractService<Agendamento, AgendamentoRepository>{
	
	@Autowired
	private ProcedimentoService procedimentoService;
	
	@Autowired
	private OrcamentoService orcamentoService;
	
	
	@Override
	public Agendamento salvar(Agendamento objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
		
		if(objeto.getHoraFim() == null) {
			objeto.setHoraFim(adicionarTempoDeProcedimentoNaHora(objeto.getHoraInicio(), objeto.getProcedimento().getDuracaoMinutos()));
		}
		
		dataDeAgendamentoMaiorIgualQueDataAtual(objeto.getDataAgendamento());
		horaInicialMenorQueFinal(objeto.getHoraInicio(), objeto.getHoraFim());
		
		agendamentoJaCadastradoNesseHorario(objeto, null);
		
		orcamentoEstaAprovado(objeto.getOrcamento());
		procedimentoEstaNoOrcamento(objeto);
		

		return repository.save(objeto);
	}
	
	
	@Override
	public Agendamento atualizar(Long codigo, Agendamento objetoModificado) throws ServiceApplicationException{
		
		if(objetoModificado.getHoraFim() == null) {
			objetoModificado.setHoraFim(adicionarTempoDeProcedimentoNaHora(objetoModificado.getHoraInicio(), objetoModificado.getProcedimento().getDuracaoMinutos()));
		}
		
		dataDeAgendamentoMaiorIgualQueDataAtual(objetoModificado.getDataAgendamento());
		horaInicialMenorQueFinal(objetoModificado.getHoraInicio(), objetoModificado.getHoraFim());
		
		if(!(objetoModificado.getStatusAgendamento() == StatusAgendamento.CANCELADO)) {
			agendamentoJaCadastradoNesseHorario(objetoModificado, codigo);
		}
		orcamentoEstaAprovado(objetoModificado.getOrcamento());
		procedimentoEstaNoOrcamento(objetoModificado);
		
		Agendamento objetoAtualizado = buscarPorCodigo(codigo);
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo");
		return repository.save(objetoAtualizado);
	}
	
	
	
	
	
	public Agendamento fromDTO(AgendamentoNovoDTO objetoDTO) throws ServiceApplicationException {
		
		Date horaInicio = converterStringParaHora(objetoDTO.getHoraInicio());
		Date horaFim = null;
		if(objetoDTO.getHoraFim() != null) {
			horaFim = converterStringParaHora(objetoDTO.getHoraFim());
		}
		
		Orcamento orcamento = orcamentoService.buscarPorCodigo(objetoDTO.getCodigoOrcamento());
		
		Procedimento procedimento = procedimentoService.buscarPorCodigo(objetoDTO.getCodigoProcedimento());
		
		
		Agendamento agendamento = new Agendamento(objetoDTO.getDataAgendamento(), horaInicio, horaFim, StatusAgendamento.toEnum(objetoDTO.getStatusAgendamento()), 
				objetoDTO.getObservacao(), objetoDTO.getPrimeiraAvalicao(), orcamento , procedimento);
				
		return agendamento;
	}
	
	
	private Date converterStringParaHora(String hora) throws DadoInvalidoException {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	    try {
			return dateFormat.parse(hora);
		} catch (ParseException e) {
			throw new DadoInvalidoException("Erro na conversão da hora: " + hora);
		}
	}
	
	private void dataDeAgendamentoMaiorIgualQueDataAtual(Date dataAgendamento) throws DataAgendamentoInvalidaException {
		Calendar calendar = new GregorianCalendar();
		Date dataAtual = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		
		String dataAtualString = dateFormat.format(dataAtual);
		String dataAgendamentoString = dateFormat.format(dataAgendamento);
		
		try {
			dataAtual = dateFormat.parse(dataAtualString);
			dataAgendamento = dateFormat.parse(dataAgendamentoString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int resultado = dataAgendamento.compareTo(dataAtual);
		
		if(resultado < 0) {
			throw new DataAgendamentoInvalidaException("A data de agendamento "+"'"+dataAgendamentoString+"'" +" não pode ser inferior a data atual.");
		}
		
	}
	
	
	private void horaInicialMenorQueFinal (Date horaInicial, Date horaFinal) throws HoraAgendamentoInvalidaException {
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		
		String horaInicialString =  dateFormat.format(horaInicial);
		String horaFinalString =  dateFormat.format(horaFinal);
		
		try {
			horaInicial = dateFormat.parse(horaInicialString);
			horaFinal = dateFormat.parse(horaFinalString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(!horaInicial.before(horaFinal)) {
			throw new HoraAgendamentoInvalidaException("A hora de inicio "+"'"+horaInicialString+"'"+ "não pode ser superior ou igual a hora de finalização " +"'"+horaFinalString+"'");
		}
	}
	
	private Date adicionarTempoDeProcedimentoNaHora(Date horaInicial, Integer tempoProcedimento) {
	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(horaInicial);
		calendar.add(Calendar.MINUTE, tempoProcedimento);	
		
		return calendar.getTime();
	}
	
	
	private boolean agendamentoJaCadastradoNesseHorario(Agendamento objeto, Long codigoDoObjetoAtualizado) throws AgendamentoJaCadastradoNoHorarioException {
		AgendamentoFilter filter = new AgendamentoFilter();
		filter.setDataAgendamento(objeto.getDataAgendamento());
		
		filter.setHoraInicioMin(objeto.getHoraInicio());
		filter.setHoraInicioMax(objeto.getHoraFim());
		
		List<Agendamento> listaDeObjetos = repository.buscarPorHoraEData(filter);
		
		if(listaDeObjetos.isEmpty()) {
			return false;
		} else {
			Agendamento agendamentoExistente = null;
			for(Agendamento a: listaDeObjetos) {
				agendamentoExistente = a;
				if(codigoDoObjetoAtualizado != null) {
					if(codigoDoObjetoAtualizado != agendamentoExistente.getCodigo() && !(agendamentoExistente.getStatusAgendamento() == StatusAgendamento.CANCELADO)) {
						throw new AgendamentoJaCadastradoNoHorarioException(Long.toString(agendamentoExistente.getCodigo()));			
					}
				}
				else {
					if(!(agendamentoExistente.getStatusAgendamento() == StatusAgendamento.CANCELADO)) {
						throw new AgendamentoJaCadastradoNoHorarioException(Long.toString(agendamentoExistente.getCodigo()));
					}
				}
				
			}
				
		}
		return false;
		
	}
	
	
	public List<Agendamento> filtrar(AgendamentoFilter filter){
		filter.setHoraInicioMax(null);
		filter.setHoraInicioMin(null);
			
		return repository.buscarPorFiltro(filter);
	}
	
	public void procedimentoEstaNoOrcamento(Agendamento a) throws ProcedimentoNaoEstaEmOrcamentoException {
		Orcamento orcamento = a.getOrcamento();
		boolean resultado = false;
		for(Procedimento p: orcamento.getProcedimentos()) {
			if(p.getCodigo() == a.getProcedimento().getCodigo()) {
				resultado = true;
			}
		}
		if(resultado == false) {
			throw new ProcedimentoNaoEstaEmOrcamentoException("O procedimento '"+a.getProcedimento().getNome() +"' não está no orçamento");
		}
	}
	
	
	public void orcamentoEstaAprovado(Orcamento o) throws OrcamentoNaoAprovadoException {
		if(o.getAprovado() == false) {
			throw new OrcamentoNaoAprovadoException("Não poderá agendar procedimentos enquanto o orçamento não for aprovado.");
		}
	}
	
	

}

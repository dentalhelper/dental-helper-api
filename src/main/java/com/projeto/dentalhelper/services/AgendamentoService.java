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
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.domains.enums.StatusAgendamento;
import com.projeto.dentalhelper.dtos.AgendamentoNovoDTO;
import com.projeto.dentalhelper.repositories.AgendamentoRepository;
import com.projeto.dentalhelper.repositories.filter.AgendamentoFilter;
import com.projeto.dentalhelper.services.exceptions.AgendamentoJaCadastradoNoHorarioException;
import com.projeto.dentalhelper.services.exceptions.DadoInvalidoException;
import com.projeto.dentalhelper.services.exceptions.DataAgendamentoInvalidaException;
import com.projeto.dentalhelper.services.exceptions.HoraAgendamentoInvalidaException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class AgendamentoService extends AbstractService<Agendamento, AgendamentoRepository>{
	
	@Autowired
	PacienteService pacienteService;
	
	@Autowired
	ProcedimentoService procedimentoService;
	
	private static final int PRIMEIRO_ITEM = 0;
	
	
	@Override
	public Agendamento salvar(Agendamento objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
		
		if(objeto.getHoraFim() == null) {
			objeto.setHoraFim(adicionarTempoDeProcedimentoNaHora(objeto.getHoraInicio(), objeto.getProcedimento().getDuracaoMinutos()));
		}
		
		dataDeAgendamentoMaiorIgualQueDataAtual(objeto.getDataAgendamento());
		horaInicialMenorQueFinal(objeto.getHoraInicio(), objeto.getHoraFim());
		
		agendamentoJaCadastradoNesseHorario(objeto, null);

		return repository.save(objeto);
	}
	
	
	@Override
	public Agendamento atualizar(Long codigo, Agendamento objetoModificado) throws ServiceApplicationException{
		
		if(objetoModificado.getHoraFim() == null) {
			objetoModificado.setHoraFim(adicionarTempoDeProcedimentoNaHora(objetoModificado.getHoraInicio(), objetoModificado.getProcedimento().getDuracaoMinutos()));
		}
		
		dataDeAgendamentoMaiorIgualQueDataAtual(objetoModificado.getDataAgendamento());
		horaInicialMenorQueFinal(objetoModificado.getHoraInicio(), objetoModificado.getHoraFim());
		
		agendamentoJaCadastradoNesseHorario(objetoModificado, codigo);
		
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
		
		Paciente paciente = pacienteService.buscarPorCodigo(objetoDTO.getCodigoPaciente());
		
		Procedimento procedimento = procedimentoService.buscarPorCodigo(objetoDTO.getCodigoProcedimento());
		
		
		Agendamento agendamento = new Agendamento(objetoDTO.getDataAgendamento(), horaInicio, horaFim, StatusAgendamento.toEnum(objetoDTO.getStatusAgendamento()), 
				objetoDTO.getObservacao(), objetoDTO.getPrimeiraAvalicao(), paciente , procedimento);
				
		return agendamento;
	}
	
	
	private Date converterStringParaHora(String hora) throws DadoInvalidoException {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
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
		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		
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
			Agendamento agendamentoExistente = obterPacienteExistente(listaDeObjetos);
			if(codigoDoObjetoAtualizado != null) {
				if(agendamentoExistente.getCodigo() == codigoDoObjetoAtualizado) {
					return false;
				}
			}
			throw new AgendamentoJaCadastradoNoHorarioException(Long.toString(agendamentoExistente.getCodigo()));
		}
		
	}
	
	private Agendamento obterPacienteExistente(List<Agendamento> listaDeObjetos) {
		return listaDeObjetos.get(PRIMEIRO_ITEM);
	}
	
	
	

}

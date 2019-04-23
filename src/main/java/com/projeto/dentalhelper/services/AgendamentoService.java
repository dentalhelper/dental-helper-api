package com.projeto.dentalhelper.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.domains.enums.StatusAgendamento;
import com.projeto.dentalhelper.dtos.AgendamentoNovoDTO;
import com.projeto.dentalhelper.repositories.AgendamentoRepository;
import com.projeto.dentalhelper.services.exceptions.DadoInvalidoException;
import com.projeto.dentalhelper.services.exceptions.DataAgendamentoInvalidaException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class AgendamentoService extends AbstractService<Agendamento, AgendamentoRepository>{
	
	
	
	@Override
	public Agendamento salvar(Agendamento objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
		dataDeAgendamentoMaiorQueDataAtual(objeto.getDataAgendamento());

		return repository.save(objeto);
	}
	
	
	@Override
	public Agendamento atualizar(Long codigo, Agendamento objetoModificado) throws ServiceApplicationException{
		
		dataDeAgendamentoMaiorQueDataAtual(objetoModificado.getDataAgendamento());
		
		Agendamento objetoAtualizado = buscarPorCodigo(codigo);
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo");
		return repository.save(objetoAtualizado);
	}
	
	
	
	
	
	public Agendamento fromDTO(AgendamentoNovoDTO objetoDTO) throws DadoInvalidoException {
		
		Date horaInicio = converterStringParaHora(objetoDTO.getHoraInicio());
		Date horaFim = converterStringParaHora(objetoDTO.getHoraFim());
		
		Paciente paciente = new Paciente();
		paciente.setCodigo(objetoDTO.getCodigoPaciente());
		
		Procedimento procedimento = new Procedimento();
		procedimento.setCodigo(objetoDTO.getCodigoProcedimento());
		
		
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
	
	private void dataDeAgendamentoMaiorQueDataAtual(Date dataAgendamento) throws DataAgendamentoInvalidaException {
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
	
	
	

}

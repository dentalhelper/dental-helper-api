package com.projeto.dentalhelper.services;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.repositories.PacienteRepository;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class PacienteService extends AbstractService<Paciente, PacienteRepository>{
	
	@Override
	public Paciente salvar(Paciente objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
		Calendar calendar = new GregorianCalendar();
		objeto.setDataCriacaoFicha(calendar.getTime());
		
		
		
		
		return repository.save(objeto);
	}

}

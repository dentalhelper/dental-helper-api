package com.projeto.dentalhelper.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Cidade;
import com.projeto.dentalhelper.repositories.CidadeRepository;
import com.projeto.dentalhelper.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public List<Cidade> buscarPorEstado(Long codigoEstado) {
		List<Cidade> cidades = repository.findCidades(codigoEstado);
		if (cidades == null) {
			throw new ObjetoNaoEncontradoException("Nenhuma Cidade foi encontrada.");
		}
		return cidades;

	}
}

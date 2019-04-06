package com.projeto.dentalhelper.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Estado;
import com.projeto.dentalhelper.repositories.EstadoRepository;
import com.projeto.dentalhelper.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	public Estado buscarPorId(Long codigo) {
		Optional<Estado> objeto = repository.findById(codigo);
		return objeto.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Estado não encontrado! Id: " + codigo + ", Tipo: " + Estado.class.getName()));
	}

	public List<Estado> buscarTodos() {
		List<Estado> estados = repository.findAllByOrderByNome();
		if (estados == null) {
			throw new ObjetoNaoEncontradoException("Nenhum Estado foi encontrado.");
		}
		return estados;

	}
}

package com.projeto.dentalhelper.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.dentalhelper.domains.Usuario;
import com.projeto.dentalhelper.repositories.Usuario.UsuarioRepositoryQuery;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery{
	
	public Optional<Usuario> findByLogin(String login);
}

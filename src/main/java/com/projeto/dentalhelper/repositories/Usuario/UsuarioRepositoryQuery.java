package com.projeto.dentalhelper.repositories.Usuario;

import java.util.List;

import com.projeto.dentalhelper.domains.Usuario;
import com.projeto.dentalhelper.repositories.filter.UsuarioFilter;

public interface UsuarioRepositoryQuery {
	
	public List<Usuario> buscarPorCpf(UsuarioFilter Filter);
	
	public List<Usuario> buscarPorRg(UsuarioFilter filter);
	
	public List<Usuario> filtrar(UsuarioFilter filter);
	
	public List<Usuario> buscarPorLogin(String login);

}

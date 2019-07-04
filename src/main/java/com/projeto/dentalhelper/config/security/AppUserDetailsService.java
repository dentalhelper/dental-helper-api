package com.projeto.dentalhelper.config.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Usuario;
import com.projeto.dentalhelper.repositories.UsuarioRepository;


@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		if(usuario.getAtivo()==false) {
			throw new UsernameNotFoundException("Usuário está desativado");
		}
		return new User(login, usuario.getSenha(), getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(usuario.getTipo().getDescricao().toUpperCase()));
		return authorities;
	}
	
//	public Usuario getUserLogged() {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String nome= null;
//		if (principal instanceof UserDetails) {
//		     nome = ((UserDetails)principal).getUsername();
//		} else {
//		     nome = principal.toString();
//		}
//		Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(nome);
//		return usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Login não encontrado"));
//		
//	}
}

package com.projeto.dentalhelper.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Usuario;
import com.projeto.dentalhelper.dtos.UsuarioAlteraSenhaDTO;
import com.projeto.dentalhelper.dtos.UsuarioNovoDTO;
import com.projeto.dentalhelper.resources.api.UsuarioAPI;
import com.projeto.dentalhelper.services.UsuarioService;
import com.projeto.dentalhelper.services.exceptions.ConfirmacaoDeSenhaIncorretaException;
import com.projeto.dentalhelper.services.exceptions.ConfirmacaoDeSenhaIncorretaRuntimeException;
import com.projeto.dentalhelper.services.exceptions.CpfJaCadastradoException;
import com.projeto.dentalhelper.services.exceptions.CpfJaCadastradoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.DadoInvalidoException;
import com.projeto.dentalhelper.services.exceptions.DadoInvalidoRunTimeException;
import com.projeto.dentalhelper.services.exceptions.EmailIncorretoException;
import com.projeto.dentalhelper.services.exceptions.EmailIncorretoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.EmailInvalidoException;
import com.projeto.dentalhelper.services.exceptions.EmailInvalidoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.EmailNaoEnviadoException;
import com.projeto.dentalhelper.services.exceptions.EmailNaoEnviadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.RecursoCpfDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoCpfDuplicadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.RecursoEmailDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoEmailDuplicadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.RecursoLoginDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoLoginDuplicadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.RecursoRgDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoRgDuplicadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.RgJaCadastradoException;
import com.projeto.dentalhelper.services.exceptions.RgJaCadastradoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.SenhaIncorretaException;
import com.projeto.dentalhelper.services.exceptions.SenhaIncorretaRuntimeException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class UsuarioResource extends AbstractResource<Usuario, UsuarioService> implements UsuarioAPI{

	@Override
	public ResponseEntity<Usuario> post(@Valid UsuarioNovoDTO objetoDTO, HttpServletResponse response) {
		Usuario objetoSalvo = service.fromDTO(objetoDTO);

		try {
			objetoSalvo = salvar(objetoSalvo);
		} catch (DadoInvalidoException e) {
			throw new DadoInvalidoRunTimeException(e.getMessage());
		} catch (RgJaCadastradoException e) {
			throw new RgJaCadastradoRuntimeException(e.getMessage());
		} catch (CpfJaCadastradoException e) {
			throw new CpfJaCadastradoRuntimeException(e.getMessage());	
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo, "/usuarios");
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
	}
	
	private Usuario salvar(Usuario objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}

	@Override
	public List<Usuario> getAll() {
		List<Usuario> objetos = service.buscarTodos();
		return adicionarLinks(objetos);
	}

	@Override
	public ResponseEntity<Usuario> put(Long codigo, @Valid UsuarioNovoDTO objetoDTO) {
		Usuario usuarioFromDTO = service.fromDTO(objetoDTO);
		usuarioFromDTO.setCodigo(codigo);
		Usuario objetoEditado = null;
		try {
			objetoEditado = atualizar(codigo, usuarioFromDTO);
		} catch (DadoInvalidoException e) {
			throw new DadoInvalidoRunTimeException(e.getMessage());
		} catch (RgJaCadastradoException e) {
			throw new RgJaCadastradoRuntimeException(e.getMessage());
		} catch (CpfJaCadastradoException e) {
			throw new CpfJaCadastradoRuntimeException(e.getMessage());
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		return ResponseEntity.ok(objetoEditado);
	}
	
	private Usuario atualizar(Long codigo, Usuario objetoModificado) throws ServiceApplicationException {
		return service.atualizar(codigo, objetoModificado);
	}
	
	@Override
	public ResponseEntity<Usuario> getByCodigo(@PathVariable Long codigo) {
		Usuario objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();

	}

	protected void lancarExceptionComLocation(ServiceApplicationException e) {
		Usuario usuarioExistente = service.buscarPorCodigo(Long.parseLong(e.getMessage()));
		adicionarLink(usuarioExistente, usuarioExistente.getCodigo());
		if (e instanceof RecursoCpfDuplicadoException) {
			throw new RecursoCpfDuplicadoRuntimeException("Já existe um Usuario com esse cpf: " + usuarioExistente.getcPF(),
					usuarioExistente.getId().getHref());
		}else if(e instanceof RecursoRgDuplicadoException) {
			throw new RecursoRgDuplicadoRuntimeException("Já existe um Usuario com esse rg: " + usuarioExistente.getrG(),
					usuarioExistente.getId().getHref());
		}else if(e instanceof RecursoLoginDuplicadoException){
			throw new RecursoLoginDuplicadoRuntimeException("Já existe um Usuario com esse login: " + usuarioExistente.getLogin(),
					usuarioExistente.getId().getHref());
		}else if(e instanceof RecursoEmailDuplicadoException){
			throw new RecursoEmailDuplicadoRuntimeException("Já existe um Usuario com esse email: " + usuarioExistente.getLogin(),
					usuarioExistente.getId().getHref());
		}
	}

	@Override
	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().header("Entity", Long.toString(codigo)).build();
	}

	@Override
	public ResponseEntity<Usuario> AlterarSenha(Long codigo, @Valid UsuarioAlteraSenhaDTO objetoDTO) {
		Usuario objetoEditado;
		try {
			objetoEditado = service.alterarSenha(objetoDTO, codigo);
		} catch (ConfirmacaoDeSenhaIncorretaException e) {
			throw new ConfirmacaoDeSenhaIncorretaRuntimeException(e.getMessage());
		} catch (SenhaIncorretaException e) {
			throw new SenhaIncorretaRuntimeException(e.getMessage());
		}
		
		return ResponseEntity.ok(objetoEditado);
	}

	@Override
	public ResponseEntity<UsuarioNovoDTO> getByCodigoForEdit(Long codigo) {
		Usuario objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		UsuarioNovoDTO usuarioDTO = new UsuarioNovoDTO(objeto);
		usuarioDTO.setSenha("******");
		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(usuarioDTO);
	}

	@Override
	public ResponseEntity<Usuario> atualizarStatus(Long codigo) throws ServiceApplicationException {
		Usuario objetoEditado = service.alterarAtivo(codigo);
		return ResponseEntity.ok(objetoEditado);
	}

	@Override
	public ResponseEntity<Usuario> redefinirSenha(@RequestBody String email) throws ServiceApplicationException {
		Usuario objetoEditado;
		try {
			objetoEditado = service.redefinirSenha(email);
		} catch (EmailInvalidoException e) {
			throw new EmailInvalidoRuntimeException(e.getMessage());
		} catch (EmailNaoEnviadoException e) {
			throw new EmailNaoEnviadoRuntimeException(e.getMessage());
		}catch (EmailIncorretoException e) {
			throw new EmailIncorretoRuntimeException(e.getMessage());
		}
		return ResponseEntity.ok(objetoEditado);
	}
	

}

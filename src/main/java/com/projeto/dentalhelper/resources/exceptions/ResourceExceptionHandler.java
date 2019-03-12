package com.projeto.dentalhelper.resources.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.projeto.dentalhelper.services.exceptions.IntegridadeDeDadosException;
import com.projeto.dentalhelper.services.exceptions.ObjetoNaoEncontradoException;
import com.projeto.dentalhelper.services.exceptions.RecursoNomeDuplicadoRuntimeException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("mensagem.invalida");
		String mensagemDesenvolvedor = ex.getCause().toString();
		List<ErroMensagem> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(ex, responseBody, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		status = HttpStatus.BAD_REQUEST;
		List<ErroMensagem> responseBody = criarListaDeErros(ex.getBindingResult(), status);
		return handleExceptionInternal(ex, responseBody, headers, status, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessExeption(EmptyResultDataAccessException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		String mensagemUsuario = montarMensagemUsuario("recurso.nao-encontrado");
		String mensagemDesenvolvedor = exception.toString();
		List<ErroMensagem> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), status, request);
	}

	@ExceptionHandler({ ObjetoNaoEncontradoException.class })
	public ResponseEntity<Object> objetoNaoEncontrado(ObjetoNaoEncontradoException exception,
			HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		String mensagemUsuario = montarMensagemUsuario("recurso.nao-encontrado");
		String mensagemDesenvolvedor = exception.toString();
		List<ErroMensagem> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return ResponseEntity.status(status).body(responseBody);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("recurso.operacao-nao-permitida");
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(exception);
		List<ErroMensagem> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(IntegridadeDeDadosException.class)
	public ResponseEntity<Object> integridadeDeDadosViolada(IntegridadeDeDadosException exception,
			HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("recurso.integridade-violada");
		String mensagemDesenvolvedor = exception.toString();
		List<ErroMensagem> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return ResponseEntity.status(status).body(responseBody);
	}

	@ExceptionHandler(RecursoNomeDuplicadoRuntimeException.class)
	public ResponseEntity<Object> recursoNomeDuplicado(RecursoNomeDuplicadoRuntimeException exception,
			WebRequest request, HttpServletResponse response) {

		HttpStatus status = HttpStatus.CONFLICT;
		String mensagemUsuario = montarMensagemUsuario("recurso.nome-duplicado");
		String mensagemDesenvolvedor = exception.toString();
		List<ErroMensagem> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);
		
		return ResponseEntity.status(status).header("Location", exception.getLinkRecurso()).body(responseBody);
	}

	private List<ErroMensagem> montarResponseBody(HttpStatus status, String mensagemUsuario,
			String mensagemDesenvolvedor) {
		return Arrays.asList(
				new ErroMensagem(mensagemUsuario, mensagemDesenvolvedor, status.value(), System.currentTimeMillis()));
	}

	private String montarMensagemUsuario(String sourceMessage) {
		return messageSource.getMessage(sourceMessage, null, LocaleContextHolder.getLocale());
	}

	private List<ErroMensagem> criarListaDeErros(BindingResult bindingResult, HttpStatus status) {
		List<ErroMensagem> erros = new ArrayList<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString();
			erros.add(new ErroMensagem(mensagemUsuario, mensagemDesenvolvedor, status.value(),
					System.currentTimeMillis()));
		}
		return erros;
	}
}

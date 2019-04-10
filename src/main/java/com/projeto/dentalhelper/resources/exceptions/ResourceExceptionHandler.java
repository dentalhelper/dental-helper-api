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

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.projeto.dentalhelper.services.exceptions.DadoInvalidoRunTimeException;
import com.projeto.dentalhelper.services.exceptions.FileException;
import com.projeto.dentalhelper.services.exceptions.IntegridadeDeDadosException;
import com.projeto.dentalhelper.services.exceptions.ObjetoNaoEncontradoException;
import com.projeto.dentalhelper.services.exceptions.RecursoCpfDuplicadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.RecursoDuplicadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.RecursoNomeDuplicadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.RecursoRgDuplicadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.RespostaInvalidaException;
import com.projeto.dentalhelper.services.exceptions.RespostaInvalidaRuntimeException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	private static final HttpStatus CONFLICT = HttpStatus.CONFLICT;
	private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

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
	
//	@ExceptionHandler({ RespostaInvalidaException.class })
//	public ResponseEntity<Object> respostaInvalida(RespostaInvalidaException exception,
//			HttpServletRequest request) {
//
//		HttpStatus status = HttpStatus.NOT_FOUND;
//		String mensagemUsuario = montarMensagemUsuario("recurso.resposta-invalida");
//		String mensagemDesenvolvedor = exception.toString();
//		List<ErroMensagem> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);
//
//		return ResponseEntity.status(status).body(responseBody);
//	}

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

		List<ErroMensagemConflict> responseBody = montarResponseBodyConflict(exception, "recurso.nome-duplicado");
		return ResponseEntity.status(CONFLICT).header("Location", exception.getLinkRecurso()).body(responseBody);
	}
	
	@ExceptionHandler(RespostaInvalidaRuntimeException.class)
	public ResponseEntity<Object> recursoNomeDuplicado(RespostaInvalidaRuntimeException exception,
			WebRequest request, HttpServletResponse response) {
		
		
		
		String mensagemUsuario = montarMensagemUsuario("recurso.resposta-invalida");
		String mensagemDesenvolvedor = exception.toString();
		List<ErroMensagem> responseBody = Arrays.asList(new ErroMensagem(mensagemUsuario,
				mensagemDesenvolvedor, BAD_REQUEST.value(), System.currentTimeMillis()));
		
		
		return ResponseEntity.status(BAD_REQUEST).body(responseBody);
	}
	
	@ExceptionHandler(DadoInvalidoRunTimeException.class)
	public ResponseEntity<Object> recursoNomeDuplicado(DadoInvalidoRunTimeException exception,
			WebRequest request, HttpServletResponse response) {
		
		
		
		String mensagemUsuario = montarMensagemUsuario("recurso.dado-invalido");
		String mensagemDesenvolvedor = exception.toString();
		List<ErroMensagem> responseBody = Arrays.asList(new ErroMensagem(mensagemUsuario,
				mensagemDesenvolvedor, BAD_REQUEST.value(), System.currentTimeMillis()));
		
		
		return ResponseEntity.status(BAD_REQUEST).body(responseBody);
	}
	
	

	@ExceptionHandler(RecursoCpfDuplicadoRuntimeException.class)
	public ResponseEntity<Object> recursoCpfDuplicado(RecursoCpfDuplicadoRuntimeException exception,
			WebRequest request, HttpServletResponse response) {

		List<ErroMensagemConflict> responseBody = montarResponseBodyConflict(exception, "recurso.cpf-duplicado");
		return ResponseEntity.status(CONFLICT).header("Location", exception.getLinkRecurso()).body(responseBody);
	}

	@ExceptionHandler(RecursoRgDuplicadoRuntimeException.class)
	public ResponseEntity<Object> recursoRgDuplicado(RecursoRgDuplicadoRuntimeException exception, WebRequest request,
			HttpServletResponse response) {

		List<ErroMensagemConflict> responseBody = montarResponseBodyConflict(exception, "recurso.rg-duplicado");
		return ResponseEntity.status(CONFLICT).header("Location", exception.getLinkRecurso()).body(responseBody);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<Object> arquivo(FileException exception, HttpServletRequest request){

		String mensagemUsuario = montarMensagemUsuario("file.erro");
		String mensagemDesenvolvedor = exception.toString();
		List<ErroMensagem> responseBody = montarResponseBody(BAD_REQUEST, mensagemUsuario, mensagemDesenvolvedor);
		
		return ResponseEntity.status(BAD_REQUEST).body(responseBody);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<Object> amazonService(AmazonServiceException exception, HttpServletRequest request){

		HttpStatus status = HttpStatus.valueOf(exception.getErrorCode());
		String mensagemUsuario = montarMensagemUsuario("s3.service.erro");
		String mensagemDesenvolvedor = exception.toString();
		List<ErroMensagem> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);
		
		return ResponseEntity.status(status).body(responseBody);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<Object> amazonClient(AmazonClientException exception, HttpServletRequest request){

		String mensagemUsuario = montarMensagemUsuario("file.erro");
		String mensagemDesenvolvedor = exception.toString();
		List<ErroMensagem> responseBody = montarResponseBody(BAD_REQUEST, mensagemUsuario, mensagemDesenvolvedor);
		
		return ResponseEntity.status(BAD_REQUEST).body(responseBody);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<Object> amazonS3(AmazonS3Exception exception, HttpServletRequest request){

		String mensagemUsuario = montarMensagemUsuario("file.erro");
		String mensagemDesenvolvedor = exception.toString();
		List<ErroMensagem> responseBody = montarResponseBody(BAD_REQUEST, mensagemUsuario, mensagemDesenvolvedor);
		
		return ResponseEntity.status(BAD_REQUEST).body(responseBody);
	}

	private List<ErroMensagem> montarResponseBody(HttpStatus status, String mensagemUsuario,
			String mensagemDesenvolvedor) {
		return Arrays.asList(
				new ErroMensagem(mensagemUsuario, mensagemDesenvolvedor, status.value(), System.currentTimeMillis()));
	}

	private String montarMensagemUsuario(String sourceMessage) {
		return messageSource.getMessage(sourceMessage, null, LocaleContextHolder.getLocale());
	}

	private List<ErroMensagemConflict> montarResponseBodyConflict(RecursoDuplicadoRuntimeException exception,
			String messageProperty) {
		String mensagemUsuario = montarMensagemUsuario(messageProperty);
		String mensagemDesenvolvedor = exception.toString();
		String resourceLocation = exception.getLinkRecurso();
		List<ErroMensagemConflict> responseBody = Arrays.asList(new ErroMensagemConflict(mensagemUsuario,
				mensagemDesenvolvedor, CONFLICT.value(), System.currentTimeMillis(), resourceLocation));
		return responseBody;
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

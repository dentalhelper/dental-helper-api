package com.projeto.dentalhelper.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.projeto.dentalhelper.services.exceptions.EmailNaoEnviadoException;

@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void enviarEmail(String remetente, 
			String destinatario, String assunto, String mensagem) throws EmailNaoEnviadoException {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(remetente);
			helper.setTo(destinatario);
			helper.setSubject(assunto);
			helper.setText(mensagem, true);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new EmailNaoEnviadoException("Ocorreu um erro ao tentar enviar o email.");
		}
	}
	
	public void enviarEmailComNovaSenha(String destinatario, String senha) throws EmailNaoEnviadoException {
		this.enviarEmail("dental.helper.mail@gmail.com", 
				destinatario, 
				"Redefinição de senha", "Sua senha foi redefinida para: '"+senha+"'.");
	}
	
	
}
package com.projeto.dentalhelper.domains;

import java.net.URI;

public class Foto {

	private URI uri;
	private String nome;
	
	public Foto(URI uri, String nome) {
		super();
		this.uri = uri;
		this.nome = nome;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

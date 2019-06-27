CREATE TABLE imagem_anexada(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	foto VARCHAR(500),
	url_da_foto VARCHAR(500),
	descricao VARCHAR(200),
	codigo_procedimento_previsto BIGINT(20),	
	
	 FOREIGN KEY (codigo_procedimento_previsto) REFERENCES procedimento_previsto(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;


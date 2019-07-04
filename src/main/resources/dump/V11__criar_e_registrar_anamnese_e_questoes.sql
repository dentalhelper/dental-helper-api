CREATE TABLE anamnese(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_resp DATE
	
)ENGINE=innoDB DEFAULT charset=utf8;

CREATE TABLE questao(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(135) NOT NULL,
	resposta INT(10),
	inform_adicionais VARCHAR(70),
	codigo_anamnese BIGINT(20),
	
	FOREIGN KEY (codigo_anamnese) REFERENCES anamnese(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;



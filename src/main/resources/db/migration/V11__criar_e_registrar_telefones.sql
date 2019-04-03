CREATE TABLE telefone(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	ddd INT(3),
	numero VARCHAR(15) NOT NULL,
	codigo_pessoa BIGINT(20),
	
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO telefone
(ddd, numero, codigo_pessoa) values
(81, '986754231', 1)
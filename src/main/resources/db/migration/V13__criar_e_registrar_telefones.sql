CREATE TABLE telefone(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	numero VARCHAR(15) NOT NULL,
	codigo_pessoa BIGINT(20),
	
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO telefone
(numero, codigo_pessoa) values
('83986754231', 1),
('83988585233', 1),
('84986754441', 2);
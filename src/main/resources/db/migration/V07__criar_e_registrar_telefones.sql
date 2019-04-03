CREATE TABLE telefone(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	ddd INT(3),
	numero VARCHAR(15) NOT NULL,
	codigo_paciente BIGINT(20)
	
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO telefone
(ddd, numero, codigo_paciente) values
(81, '986754231', 1),
(83, '976481543', 2),
(84, '967548372', 3),
(85, '987546123', 4),
(87, '999487562', 5)
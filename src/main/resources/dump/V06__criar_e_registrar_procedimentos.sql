CREATE TABLE procedimento(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	valor FLOAT NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	duracao INT(10) NOT NULL
	
	
	
)ENGINE=innoDB DEFAULT charset=utf8;
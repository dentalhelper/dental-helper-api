CREATE TABLE usuario(

	codigo BIGINT(20) NOT NULL,
	data_cadastro DATE NOT NULL,
	ativo BOOLEAN NOT NULL,
	login VARCHAR(500) NOT NULL,
	senha VARCHAR(500) NOT NULL,
	tipo INT(10) NOT NULL,
	
	 FOREIGN KEY (codigo) REFERENCES pessoa(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;
        
INSERT INTO usuario
(codigo, data_cadastro, ativo, login, senha, tipo) values
(1,'2019-05-27', true, 'admin00', '$2a$10$0oaz8CYI/51xqOcgEf1keOyCEBLa0pPSoXbPo19CSv58ZqVDB2mY6', 1),


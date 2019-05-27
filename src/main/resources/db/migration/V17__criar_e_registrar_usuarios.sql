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
(6,'2019-05-27', true, 'carlos1', 'carlos1', 1),
(7,'2019-05-27', true, 'renata1', 'renata1', 2),
(8,'2019-05-27', true, 'gabriel1', 'gabriel1', 3);

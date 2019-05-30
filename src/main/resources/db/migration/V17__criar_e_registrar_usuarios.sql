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
(6,'2019-05-27', true, 'carlos', '$2b$10$YPp.lNFN9nyP8RklhrJZFOXmSB6I3KSBo2TfAjwHY2AQAlkNKyCFe', 1),
(7,'2019-05-27', true, 'renata', '$2b$10$CMSsjVdZDBSrRWxV5glqGOd1AOUeRPmZZdmr7hnsoHLiNQXOw6tTi', 2),
(8,'2019-05-27', true, 'gabriel', '$2b$10$M6uEmjomLIwRsg13pcLBeu2dWR45/0focbU9ijmnaJnzvX2j8QJsC', 3);

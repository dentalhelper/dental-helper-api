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
(6,'2019-05-27', true, 'carlos', '$2a$10$yniupl2B5Spz9saUzWe6ier2KhnHkDdGjIKaxKg803En3B.VDDbpO', 1),
(7,'2019-05-27', true, 'renata', '$2a$10$kvOqpiYo3ESKIlgsEDoP8OAFgGhQQYoBSpj41xR2JCfwSaeM3Otni', 2),
(8,'2019-05-27', true, 'gabriel', '$2a$10$KYAz3LUP6BcRm3bHBxGju.oqPBmucSbYos8KoWdGoNiOICdJlfW3y', 3);

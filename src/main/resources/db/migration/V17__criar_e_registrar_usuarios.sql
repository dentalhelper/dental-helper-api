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
(6,'2019-05-27', true, 'carlos', '$2a$10$f/yXof8vAYWwT96l1T/xeexJIySrfCgtL0TOJagvG50emMtPKv/Fy', 1),
(7,'2019-05-27', true, 'renata', '$2a$10$3RkNbZbkUajLNrAeLLcRMOCmnrGPabr56/CbjJ3kslA.reZVw/FUG', 2),
(8,'2019-05-27', true, 'gabriel', '$2a$10$9SVqK9iytNY/Ap9wwkHZbOkLBem.kwBSHlxlzbmcESmY5Q5QQrRW6', 3);

CREATE TABLE cidade(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL,
	codigo_estado BIGINT(20),
	
	FOREIGN KEY (codigo_estado) REFERENCES estado(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO cidade
(nome,codigo_estado) values
('Patos',1),
('Caruaru',2),
('Salvador',3),
('Maragogi',4),
('Frei Caneca',5)
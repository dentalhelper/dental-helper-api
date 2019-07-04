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
('Maturéia',5),
('Teixeira',1),
('São Mamede',1),
('Condado',1),
('São José do Bonfim',1),
('Vista Serrana',1),
('Umbuzeiro',1),
('Sumé', 1),
('Santa Cruz do Capibaribe', 2),
('Toritama', 2),
('Jataúba', 2),
('Congo', 1);
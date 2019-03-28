CREATE TABLE atributo_material(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(20) NOT NULL,
	valor VARCHAR(30) NOT NULL,
	codigo_material BIGINT(20),
	
	FOREIGN KEY (codigo_material) REFERENCES material(codigo)
	
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO atributo_material
(nome, valor, codigo_material) values 
('Tipo','Madeira',1),
('Tipo','Micro',2),
('Pontos de Dobra','2',2),
('Tipo','III',3),
('Peso','1 Kg',3),
('Tipo','G1',4),
('Local','Gengiva ',4),
('pH','12.4',5),
('Tipo','Biodinâmica',5);
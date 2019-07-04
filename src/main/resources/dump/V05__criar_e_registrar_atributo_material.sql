CREATE TABLE atributo_material(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(20) NOT NULL,
	valor VARCHAR(30) NOT NULL,
	codigo_material BIGINT(20),
	
	FOREIGN KEY (codigo_material) REFERENCES material(codigo)
	
	
)ENGINE=innoDB DEFAULT charset=utf8;
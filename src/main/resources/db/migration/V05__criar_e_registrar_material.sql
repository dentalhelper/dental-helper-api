CREATE TABLE material(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50),
	fabricante VARCHAR(50),
	codigo_atributo_material BIGINT(20),
	
	FOREIGN KEY (codigo_atributo_material) REFERENCES  atributo_material(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO material(nome, fabricante, codigo_atributo_material) values ('pasta','colgate',1);
INSERT INTO material(nome, fabricante, codigo_atributo_material) values ('fluor','colgate',1);
INSERT INTO material(nome, fabricante, codigo_atributo_material) values ('corega','tabs',2);
INSERT INTO material(nome, fabricante, codigo_atributo_material) values ('clareador','whitenning',3);
INSERT INTO material(nome, fabricante, codigo_atributo_material) values ('braket','aroxys',1);
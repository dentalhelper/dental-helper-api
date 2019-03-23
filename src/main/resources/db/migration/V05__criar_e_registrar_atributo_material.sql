CREATE TABLE atributo_material(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(20) NOT NULL,
	valor VARCHAR(30) NOT NULL,
	codigo_material BIGINT(20),
	
	FOREIGN KEY (codigo_material) REFERENCES material(codigo)
	
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO atributo_material(nome, valor, codigo_material) values ('extra','silver',1);
INSERT INTO atributo_material(nome, valor, codigo_material) values ('baby','show',2);
INSERT INTO atributo_material(nome, valor, codigo_material) values ('vaptvolupt','treertyou',3);
INSERT INTO atributo_material(nome, valor, codigo_material) values ('ponde','frigsfriort',4);
INSERT INTO atributo_material(nome, valor, codigo_material) values ('growbs','anowwer',5);
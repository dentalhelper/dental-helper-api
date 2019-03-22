CREATE TABLE compra_material(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	quantidade BIGINT(20),
	valor FLOAT,
	codigo_material BIGINT(20),
	
	FOREIGN KEY (codigo_material) REFERENCES material(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO compra_material(quantidade, valor, codigo_material) values (1,10,1);
INSERT INTO compra_material(quantidade, valor, codigo_material) values (2,20,2);
INSERT INTO compra_material(quantidade, valor, codigo_material) values (3,300,3);
INSERT INTO compra_material(quantidade, valor, codigo_material) values (4,400,4);
INSERT INTO compra_material(quantidade, valor, codigo_material) values (5,500,5);
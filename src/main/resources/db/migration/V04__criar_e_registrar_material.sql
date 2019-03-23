CREATE TABLE material(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	fabricante VARCHAR(50)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO material(nome, fabricante) values ('pasta','colgate');
INSERT INTO material(nome, fabricante) values ('fluor','colgate');
INSERT INTO material(nome, fabricante) values ('corega','tabs');
INSERT INTO material(nome, fabricante) values ('clareador','whitenning');
INSERT INTO material(nome, fabricante) values ('braket','aroxys');
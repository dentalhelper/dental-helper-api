CREATE TABLE material(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	fabricante VARCHAR(50)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO material
(nome, fabricante) values 
('Abaixador de Língua','Estilo'),
('Aplicador','Cavibrush'),
('Gesso Pedra','Durastone'),
('Resina NT Premium','Coltene'),
('Cimento Provisório','Hydropast');
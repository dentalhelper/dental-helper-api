CREATE TABLE procedimento(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	valor FLOAT NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	duracao INT(10) NOT NULL
	
	
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO procedimento(nome, valor, descricao, duracao) values 
('Desgaste Seletivo', 20.00, ' Desgaste Seletivo - Por Hemi Arcada', 20),
('Capeamento Pulpar', 50.00, 'Capeamento Pulpar sem Restauração Final', 30),
('Faceta de Resina Fotopolimerizável ', 90.00, 'Faceta de Resina Fotopolimerizável', 50),
('Restauração de Ionômero de Vidro', 40.00, 'Restauração de Ionômero de Vidro - Por Dente', 30),
('Restauração de Amálgama', 32.00, 'Restauração de Amálgama - 1 Face', 25);

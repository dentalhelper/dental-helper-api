CREATE TABLE categoria_despesa(
	codigo bigint(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL
) 
engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria_despesa 
(nome) VALUES 
('Internet'),
('Alimentação'),
('Materiais'),
('Salário');
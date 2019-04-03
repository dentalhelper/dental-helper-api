CREATE TABLE estado(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO estado
(nome) values
('Paraíba'),
('Pernambuco'),
('Bahia'),
('Alagoas'),
('Maranhão')
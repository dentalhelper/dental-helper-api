CREATE TABLE endereco(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	logradouro VARCHAR(30) NOT NULL,
	numero INT,
	bairro VARCHAR(30) NOT NULL,
	cep VARCHAR(30) NOT NULL,
	complemento VARCHAR(30),
	codigo_cidade BIGINT(20),
	
	FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo)
	
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO endereco
(logradouro, numero, bairro, cep, complemento, codigo_cidade) values
('rua assis pereira', 43, 'centro', '55195-000', 'gafout', 1),
('rua gonçalves altair', 21, 'centro', '55192-000', 'vizinho a igreja', 2),
('rua pedrosa', 123, 'cohab', '55199-000', 'casa', 3),
('avenida artur silva', 99, 'pedroso', '55198-080', 'proximo a ponte', 4),
('avenida maria', 90, 'novo', '55290-088', 'apartamento', 5)
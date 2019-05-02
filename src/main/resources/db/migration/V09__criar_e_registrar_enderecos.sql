CREATE TABLE endereco(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	logradouro VARCHAR(30) NOT NULL,
	numero VARCHAR(30) NOT NULL,
	bairro VARCHAR(30) NOT NULL,
	cep VARCHAR(30) NOT NULL,
	complemento VARCHAR(30),
	codigo_cidade BIGINT(20),
	
	FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo)
	
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO endereco
(logradouro, numero, bairro, cep, complemento, codigo_cidade) values
('rua assis pereira', '43', 'centro', '55195000', 'gafout', 1),
('rua gonçalves altair', '21', 'centro', '55192000', 'vizinho a igreja', 2),
('rua pedrosa', '123', 'cohab', '55199000', 'casa', 3),
('avenida artur silva', '99', 'pedroso', '55198080', 'proximo a ponte', 4),
('avenida maria', '90', 'novo', '55290088', 'apartamento', 5)
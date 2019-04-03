CREATE TABLE anamnese(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_anamnese DATE,
	codigo_questao BIGINT(20),
	
	FOREIGN KEY (codigo_questao) REFERENCES questao(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO anamnese
(data_anamnese, codigo_questao) values
('2019-03-10', 1),
('2019-03-10', 2),
('2019-03-10', 3),
('2019-03-10', 4),
('2019-03-10', 5)
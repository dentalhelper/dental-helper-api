CREATE TABLE orcamento(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_orcamento DATE NOT NULL,
	aprovado BOOLEAN NOT NULL,
	valor_total FLOAT NOT NULL,
	codigo_paciente BIGINT(20),
	desconto FLOAT NOT NULL,
	status INT(10) NOT NULL,
	
	
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

CREATE TABLE procedimento_previsto(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_inicio DATE,
	data_finalizacao DATE,
	finalizado BOOLEAN NOT NULL,
	valor_procedimento FLOAT NOT NULL,
	codigo_procedimento BIGINT(20),
	face_dente INT(10) NOT NULL,
	codigo_orcamento BIGINT(20),
	
	FOREIGN KEY (codigo_procedimento) REFERENCES procedimento(codigo),
	FOREIGN KEY (codigo_orcamento) REFERENCES orcamento(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO orcamento
(data_orcamento, aprovado,valor_total, codigo_paciente, desconto, status) values
('2019-05-01', true, 75.00 , 1, 0, 2),
('2019-05-02', true, 78.00 , 2, 0, 2),
('2019-05-03', true, 55.00 , 3, 0, 2),
('2019-05-04', true, 60.00 , 4, 0, 2),
('2019-05-05', true, 45.00 , 5, 0, 2);

INSERT INTO procedimento_previsto
(data_inicio, finalizado, valor_procedimento, codigo_orcamento, codigo_procedimento, face_dente) values
('2019-05-13', false, 45, 1, 1, 1),
('2019-05-13', false, 55, 2, 3, 2),
('2019-05-14', false, 35, 3, 1, 11),
('2019-05-14', true, 55, 4, 5, 12),
('2019-05-15', false, 32, 5, 2, 3),
('2019-05-06', false, 45, 4, 4, 4),
('2019-05-03', false, 55, 1, 2, 5),
('2019-05-04', false, 75, 2, 4, 6),
('2019-05-05', false, 45, 3, 2, 7);


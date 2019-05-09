CREATE TABLE orcamento(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_orcamento DATE NOT NULL,
	aprovado BOOLEAN NOT NULL,
	valor_total FLOAT NOT NULL,
	codigo_paciente BIGINT(20),
	
	
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;


INSERT INTO orcamento
(data_orcamento, aprovado, valor_total, codigo_paciente) values
('2019-05-01', true, 75.00 , 1),
('2019-05-02', true, 78.00 , 2),
('2019-05-03', true, 55.00 , 3),
('2019-05-04', true, 60.00 , 4),
('2019-05-05', true, 45.00 , 5);


CREATE TABLE orcamento_procedimento(

	codigo_orcamento BIGINT(20) NOT NULL,
	codigo_procedimento BIGINT(20) NOT NULL,
	
	FOREIGN KEY (codigo_orcamento) REFERENCES orcamento(codigo),
	FOREIGN KEY (codigo_procedimento) REFERENCES procedimento(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO orcamento_procedimento
(codigo_orcamento, codigo_procedimento) values
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 1),
(3, 2),
(4, 5),
(4, 4),
(5, 2);


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


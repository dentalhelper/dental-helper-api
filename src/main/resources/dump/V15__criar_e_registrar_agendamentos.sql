CREATE TABLE agendamento(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_agendamento DATE NOT NULL,
	hora_inicio TIME NOT NULL,
	hora_fim TIME NOT NULL,
	status_agendamento INT(10) NOT NULL,
	observacao VARCHAR(70) NOT NULL,
	primeira_avaliacao BOOLEAN NOT NULL,
	codigo_orcamento BIGINT(20),
	codigo_procedimento BIGINT(20),
	valor FLOAT NOT NULL,
	
	
	FOREIGN KEY (codigo_orcamento) REFERENCES orcamento(codigo),
	FOREIGN KEY (codigo_procedimento) REFERENCES procedimento(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

CREATE TABLE agendamento(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_agendamento DATE NOT NULL,
	hora_inicio TIME NOT NULL,
	hora_fim TIME NOT NULL,
	status_agendamento INT(10) NOT NULL,
	observacao VARCHAR(70) NOT NULL,
	primeira_avaliacao BOOLEAN NOT NULL,
	codigo_paciente BIGINT(20),
	codigo_procedimento BIGINT(20),
	
	
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
	FOREIGN KEY (codigo_procedimento) REFERENCES procedimento(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;


INSERT INTO agendamento
(data_agendamento, hora_inicio, hora_fim, status_agendamento, observacao, primeira_avaliacao, codigo_paciente, codigo_procedimento) values
('2019-04-13', '14:00', '14:30', 1, '', true, 1, 1),
('2019-04-13', '13:00', '13:30', 2, '', false, 2, 2),
('2019-04-14', '15:00', '15:30', 3, '', true, 3, 3),
('2019-04-14', '17:00', '17:30', 4, '', false, 4, 4),
('2019-04-15', '09:00', '09:30', 5, '', true, 5, 5);

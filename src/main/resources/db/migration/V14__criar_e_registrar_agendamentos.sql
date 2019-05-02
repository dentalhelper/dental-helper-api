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
('2019-05-13', '14:30', '15:00', 1, '', true, 1, 1),
('2019-05-13', '13:00', '14:20', 2, '', false, 2, 2),
('2019-05-14', '15:00', '16:30', 3, '', true, 3, 3),
('2019-05-14', '17:00', '18:50', 4, '', false, 4, 4),
('2019-05-15', '09:00', '10:30', 5, '', true, 5, 5),
('2019-05-02', '17:00', '18:30', 2, '', false, 4, 4),
('2019-05-03', '09:00', '11:00', 1, '', true, 1, 4),
('2019-05-02', '09:00', '11:00', 3, '', false, 2, 2),
('2019-05-02', '11:00', '12:30', 4, '', true, 3, 1);

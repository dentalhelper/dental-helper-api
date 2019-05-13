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


INSERT INTO agendamento
(data_agendamento, hora_inicio, hora_fim, status_agendamento, observacao, primeira_avaliacao, valor ,codigo_orcamento, codigo_procedimento) values
('2019-05-13', '14:30', '15:00', 1, '', true, 45, 1, 1),
('2019-05-13', '13:00', '14:20', 2, '', false, 55, 2, 3),
('2019-05-14', '15:00', '16:30', 3, '', true, 35, 3, 1),
('2019-05-14', '17:00', '18:50', 4, '', false, 55, 4, 5),
('2019-05-15', '09:00', '10:30', 5, '', true, 32, 5, 2),
('2019-05-02', '17:00', '18:30', 2, '', false, 45, 4, 4),
('2019-05-03', '09:00', '11:00', 1, '', true, 55, 1, 2),
('2019-05-02', '09:00', '11:00', 3, '', false, 75, 2, 4),
('2019-05-02', '11:00', '12:30', 4, '', true, 45, 3, 2);

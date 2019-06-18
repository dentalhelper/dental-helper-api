CREATE TABLE dente(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	numero INT NOT NULL,
	observacao VARCHAR(70),
	existente BOOLEAN NOT NULL,
	codigo_paciente BIGINT(20),	
	
	 FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;


CREATE TABLE dente_procedimento_previsto (

	codigo_dente BIGINT(20),
	codigo_procedimento_previsto BIGINT(20),
	
	FOREIGN KEY (codigo_dente) REFERENCES dente(codigo),
	FOREIGN KEY (codigo_procedimento_previsto) REFERENCES procedimento_previsto(codigo)
        

)ENGINE=innoDB DEFAULT charset=utf8;


INSERT INTO dente
(nome, numero, existente, codigo_paciente) values
('1', 1, true, 1),
('2', 2, true, 1),
('3', 3, true, 1),
('4', 4, true, 1),
('5', 5, true, 1),
('6', 6, true, 1),
('7', 7, true, 1),
('8', 8, true, 1),
('9', 9, true, 1),
('10', 10, true, 1),
('11', 11, true, 1),
('12', 12, true, 1),
('13', 13, true, 1),
('14', 14, true, 1),
('15', 15, true, 1),
('16', 16, true, 1),
('17', 17, true, 1),
('18', 18, true, 1),
('19', 19, true, 1),
('20', 20, true, 1),
('21', 21, true, 1),
('22', 22, true, 1),
('23', 23, true, 1),
('24', 24, true, 1),
('25', 25, true, 1),
('26', 26, true, 1),
('27', 27, true, 1),
('28', 28, true, 1),
('29', 29, true, 1),
('30', 30, true, 1),
('31', 31, true, 1),
('32', 32, true, 1),
('33', 33, true, 1),
('34', 34, true, 1),
('35', 35, true, 1),
('36', 36, true, 1),
('37', 37, true, 1);

INSERT INTO dente_procedimento_previsto
(codigo_dente, codigo_procedimento_previsto) values
(1,1);


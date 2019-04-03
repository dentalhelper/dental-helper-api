CREATE TABLE paciente(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_criacao_ficha DATE NOT NULL,
	profissao VARCHAR(30) NOT NULL,
	cor_dos_dentes VARCHAR(30),
	forma_rosto INT,
	escala_dentes VARCHAR(30),
	foto_perfil VARCHAR(100),
	anamnese_codigo BIGINT(20),
	
	FOREIGN KEY (anamnese_codigo) REFERENCES anamnese(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO paciente
(data_criacao_ficha, profissao, cor_dos_dentes, forma_rosto, escala_dentes, foto_perfil, anamnese_codigo) values
('2019-03-10', 'pedreiro', 'branco', 1, '7,2', '01010100', 1),
('2019-03-10', 'marceneiro', 'amarelado', 2, '4,6', '0001010001', 1),
('2019-03-10', 'estudante', 'branco', 1, '8,8', '0001010101010', 3),
('2019-03-10', 'comerciante', 'amarelado', 1, '9', '1110010100', 1),
('2019-03-10', 'vendedor', 'branco', 4, '7,9', '1000100011', 5)
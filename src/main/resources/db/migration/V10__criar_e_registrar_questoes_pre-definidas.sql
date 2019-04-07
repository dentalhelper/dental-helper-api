CREATE TABLE questao_pre_definida(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	questao VARCHAR(70) NOT NULL
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO questao_pre_definida
(questao) values
('Tem algum tipo de alergia?'),
('Possui diabetes?'),
('Usa Fio dental?'),
('Já foi submetido à alguma cirurgia?'),
('Já tomou anestesia?');
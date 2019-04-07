CREATE TABLE anamnese(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_resp DATE
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO anamnese
(data_resp) values
('2019-03-10'),
('2019-03-12'),
('2019-03-13'),
('2019-03-14'),
('2019-03-15');

CREATE TABLE questao(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(70) NOT NULL,
	resposta INT(10),
	inform_adicionais VARCHAR(70),
	codigo_anamnese BIGINT(20),
	
	FOREIGN KEY (codigo_anamnese) REFERENCES anamnese(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO questao
(descricao, resposta, inform_adicionais, codigo_anamnese) values
('Tem algum tipo de alergia?',2,'', 1),
('Possui diabetes?', 2,'' ,1),
('Usa Fio dental?', 1,'' ,1),
('Já foi submetido à alguma cirurgia?', 1,'', 1),
('Já tomou anestesia?', 1,'', 1),

('Tem algum tipo de alergia?', 2,'', 2),
('Possui diabetes?', 1,'', 2),
('Usa Fio dental?', 1,'', 2),
('Já foi submetido à alguma cirurgia?',2,'', 2),
('Já tomou anestesia?', 2,'', 2),

('Tem algum tipo de alergia?', 1,'Camarão', 3),
('Possui diabetes?', 2,'', 3),
('Usa Fio dental?', 1,'', 3),
('Já foi submetido à alguma cirurgia?', 1,'', 3),
('Já tomou anestesia?', 1,'', 3),

('Tem algum tipo de alergia?', 2,'', 4),
('Possui diabetes?', 2,'', 4),
('Usa Fio dental?', 1,'', 4),
('Já foi submetido à alguma cirurgia?', 1,'', 4),
('Já tomou anestesia?', 1,'', 4),

('Tem algum tipo de alergia?', 2,'', 5),
('Possui diabetes?', 2,'', 5),
('Usa Fio dental?', 1,'', 5),
('Já foi submetido à alguma cirurgia?', 1,'', 5),
('Já tomou anestesia?', 1,'', 5);






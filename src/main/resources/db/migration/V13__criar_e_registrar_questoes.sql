CREATE TABLE questao(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	resposta BOOLEAN,
	informacoes_adicionais VARCHAR(30),
	codigo_anamnese BIGINT(20),
	
	FOREIGN KEY (codigo_anamnese) REFERENCES anamnese(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO questao
(descricao, resposta, informacoes_adicionais, codigo_anamnese) values
('já doou sangue?', true, '', 1),
('tem hepatite?', true, '', 1),
('tem diabetes?', true, '', 2),
('já fez canal?', true, '', 3),
('tem HIV?', false, '', 2)
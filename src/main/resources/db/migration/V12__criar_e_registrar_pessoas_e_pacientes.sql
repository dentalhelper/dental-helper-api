create table pessoa(
      codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
        cpf varchar(14) NOT NULL,
        rg varchar(20) NOT NULL,
        data_nascimento date not null,
        email varchar(50),
        estado_civil INT(15) NOT NULL,
        sexo INT(15) NOT NULL,
        nome varchar(50),
        endereco_codigo BIGINT(20),
        
        FOREIGN KEY (endereco_codigo) REFERENCES endereco(codigo)
        
)ENGINE=innoDB DEFAULT charset=utf8;

CREATE TABLE paciente(

	codigo BIGINT(20) NOT NULL,
	data_criacao_ficha DATE NOT NULL,
	profissao VARCHAR(30) NOT NULL,
	foto_perfil VARCHAR(100),
	url_da_foto VARCHAR(400),
	codigo_anamnese BIGINT(20),
	
	 FOREIGN KEY (codigo) REFERENCES pessoa(codigo),
	 FOREIGN KEY (codigo_anamnese) REFERENCES anamnese(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;





INSERT INTO pessoa
(cpf, rg, data_nascimento, email, estado_civil, sexo, nome, endereco_codigo) values
('11111111111', '1111111', '1997-03-10', 'aaaa@gmail.com' ,1, 2, 'João Roberto', 1),
('11111111112', '2111111', '1985-05-12', 'bbbb@gmail.com' ,2, 1, 'Julia Moraes', 2),
('11111111113', '3111111', '1993-07-08', 'cccc@gmail.com' ,3, 2, 'Douglas Nascimento', 3),
('11111111114', '4111111', '1999-12-11', 'dddd@gmail.com' ,4, 1, 'Marcela Rodrigues', 4),
('20345498000131', '5111111', '1996-03-05', 'eeee@gmail.com' ,5, 2, 'Antônio Ferreira', 5);


INSERT INTO paciente
(codigo, data_criacao_ficha, profissao, foto_perfil, url_da_foto, codigo_anamnese) values
(1,'2019-03-10','Pedreiro',  '01010100', '', 1),
(2,'2019-03-11','Recepcionista',  '01010101', '', 2),
(3,'2019-03-12','Motorista',  '01010102', '', 3),
(4,'2019-03-13','Advogada',  '01010103', '', 4),
(5,'2019-03-14','Professor',  '01010104', '', 5);
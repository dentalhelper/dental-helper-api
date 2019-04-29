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
	foto_perfil VARCHAR(500),
	url_da_foto VARCHAR(500),
	codigo_anamnese BIGINT(20),
	
	 FOREIGN KEY (codigo) REFERENCES pessoa(codigo),
	 FOREIGN KEY (codigo_anamnese) REFERENCES anamnese(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;





INSERT INTO pessoa
(cpf, rg, data_nascimento, email, estado_civil, sexo, nome, endereco_codigo) values
('98758598988', '1111111', '1997-03-10', 'aaaa@gmail.com' ,1, 2, 'João Roberto', 1),
('48798522545', '2111111', '1985-05-12', 'bbbb@gmail.com' ,2, 1, 'Julia Moraes', 2),
('58798598812', '3111111', '1993-07-08', 'cccc@gmail.com' ,3, 2, 'Douglas Nascimento', 3),
('09987896658', '4111111', '1999-12-11', 'marcelaTT@gmail.com' ,4, 1, 'Marcela Tavares', 4),
('12345678911', '5111111', '1996-03-05', 'eeee@gmail.com' ,5, 2, 'Antônio Ferreira', 5);


INSERT INTO paciente
(codigo, data_criacao_ficha, profissao, foto_perfil, url_da_foto, codigo_anamnese) values
(1,'2019-03-10','Pedreiro',  'bbddcc46-3b9f-4dd7-a777-6b9411e1ed70_joao.jpg', 'https://dental-helper.s3.sa-east-1.amazonaws.com/bbddcc46-3b9f-4dd7-a777-6b9411e1ed70_joao.jpg', 1),
(2,'2019-03-11','Recepcionista',  'f248aef7-90c1-4195-bd3c-748c488c535e_julia-sperling.jpg', 'https://dental-helper.s3.sa-east-1.amazonaws.com/f248aef7-90c1-4195-bd3c-748c488c535e_julia-sperling.jpg', 2),
(3,'2019-03-12','Motorista',  '0ddaa345-cc18-4fdd-a755-72f3b9b9d952_61TGQuDZX8L._SY606_.jpg', 'https://dental-helper.s3.sa-east-1.amazonaws.com/0ddaa345-cc18-4fdd-a755-72f3b9b9d952_61TGQuDZX8L._SY606_.jpg', 3),
(4,'2019-03-13','Advogada',  '52cd8c4a-91d9-4b45-950e-89b659aba57c_marcelatavares.jpg', 'https://dental-helper.s3.sa-east-1.amazonaws.com/52cd8c4a-91d9-4b45-950e-89b659aba57c_marcelatavares.jpg', 4),
(5,'2019-03-14','Professor',  '', 'https://s3-sa-east-1.amazonaws.com/dental-helper/noimage.png', 5);
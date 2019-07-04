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
	escala_dente VARCHAR(20),
	cor_dente VARCHAR(20),
	forma_rosto INT(15),
	
	 FOREIGN KEY (codigo) REFERENCES pessoa(codigo),
	 FOREIGN KEY (codigo_anamnese) REFERENCES anamnese(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;





INSERT INTO pessoa
(cpf, rg, data_nascimento, email, estado_civil, sexo, nome, endereco_codigo) values
('98758598987', '6111111', '1997-03-10', 'admin@gmail.com' ,1, 2, 'Admin', 1);



CREATE TABLE despesa(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_prevista DATE NOT NULL,
	data_realizada DATE,
	valor FLOAT NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	comprovante VARCHAR(50),
	codigo_categoria BIGINT(20),
	
	FOREIGN KEY (codigo_categoria) REFERENCES categoria_despesa(codigo)
    
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO despesa(data_prevista, data_realizada, valor, descricao, comprovante, codigo_categoria) values ('2017-06-11','2017-06-10', 152,'gas','comprov', 4)
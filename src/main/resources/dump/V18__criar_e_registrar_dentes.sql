CREATE TABLE dente(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
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


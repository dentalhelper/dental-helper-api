CREATE TABLE despesa(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	valor FLOAT NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	codigo_categoria BIGINT(20),
	codigo_pagamento BIGINT(20),
	
	FOREIGN KEY (codigo_categoria) REFERENCES categoria_despesa(codigo),
	FOREIGN KEY (codigo_pagamento) REFERENCES pagamento(codigo)
    
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO despesa(valor, descricao, codigo_categoria, codigo_pagamento) values (6500.00 ,'Compra de equipamentos', 3, 1);
INSERT INTO despesa(valor, descricao, codigo_categoria, codigo_pagamento) values (30.00 ,'Compra de alimentos', 2, 2);
INSERT INTO despesa(valor, descricao, codigo_categoria, codigo_pagamento) values (500.00 ,'Compra de materiais', 3, 3);
INSERT INTO despesa(valor, descricao, codigo_categoria, codigo_pagamento) values (600.00 ,'Salário da Recepcionista', 4, 4);
INSERT INTO despesa(valor, descricao, codigo_categoria, codigo_pagamento) values (150.00 , 'Internet', 1, 5);
CREATE TABLE pagamento(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_pagamento DATE NOT NULL,
	valor FLOAT NOT NULL,
	tipo INT(10) NOT NULL,
	forma INT(10) NOT NULL

)ENGINE=innoDB DEFAULT charset=utf8;
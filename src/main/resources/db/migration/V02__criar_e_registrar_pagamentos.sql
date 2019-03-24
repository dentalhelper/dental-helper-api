CREATE TABLE pagamento(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_pagamento DATE NOT NULL,
	valor FLOAT NOT NULL,
	tipo INT(10) NOT NULL,
	forma INT(10) NOT NULL

)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO pagamento 
(data_pagamento, valor, tipo, forma) values 
('2019-03-10' , 6500.00, 1, 1),
('2019-03-11' , 30.00, 1, 1),
('2019-03-12' , 500.00, 1, 2),
('2019-03-13' , 600.00, 1, 1),
('2019-03-14' , 150.00, 1, 1);

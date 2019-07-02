ALTER TABLE pagamento
  ADD  codigo_orcamento BIGINT(20) REFERENCES orcamento(codigo);
  
  
INSERT INTO pagamento 
(data_pagamento, valor, tipo, forma, codigo_orcamento) values 
('2019-07-02' , 75.00, 2, 1, 1),
('2019-07-03' , 78.00, 2, 1, 2),
('2019-07-02' , 55.00, 2, 2, 3),
('2019-07-01' , 100.00, 2, 1, 4),
('2019-07-01' , 45.00, 2, 1, 5);
        
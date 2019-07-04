CREATE TABLE questao_pre_definida(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	questao VARCHAR(135) NOT NULL
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO questao_pre_definida
(questao) values
('No momento, está sob tratamento médico?'),
('Está tomando alguma medicação no momento?'),
('Já sofreu alguma doença grave?'),
('Já foi operado(a) alguma vez?'),
('Vecê esteve sob tratamento de radioterapia ou quimioterapia?'),
('Existe algum caso de diabetes, tuberculose ou câncer na família?'),
('Já tomou anestesia local para tratar ou extrarir dentes?'),
('Alguma vez tomou penicilina ou outro antibiótico?'),
('Apresenta algum problema no coração utiliza Marca Passo ou Válvula Cardiaca Artificial?'),
('Costuma ter pernas, pés e olhos inchados?'),
('Sente falta de ar?'),
('Já fez exame de sangue para verificação de doenças sexualmente transmissíveis(sifilis, gonorréia, Herpes, AIDS ou Hepatite)?'),
('Sangra muito quando extrai dentes?'),
('Tem algum outro problema que julgue importante?'),
('Está grávida no momento?'),
('É alérgico a algum medicamento?'),
('É fumante ou ex-fumanete? Quantos cigarros cosumidos por dia?'),
('Você tem algum problema renal, hepático?'),
('Você tem hipertensão arterial(pressão alta)?'),
('Já teve febre reumática?'),
('Tem hábito de ranger ou apertar os dentes?'),
('Você costuma escovar os dentes? Quantas vezes ao dia?'),
('Usa fio dental? Usa antiséptico bucal?');
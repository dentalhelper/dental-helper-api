CREATE TABLE anamnese(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_resp DATE
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO anamnese
(data_resp) values
('2019-03-10'),
('2019-03-12'),
('2019-03-13'),
('2019-03-14'),
('2019-03-15');

CREATE TABLE questao(

	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(135) NOT NULL,
	resposta INT(10),
	inform_adicionais VARCHAR(70),
	codigo_anamnese BIGINT(20),
	
	FOREIGN KEY (codigo_anamnese) REFERENCES anamnese(codigo)
	
)ENGINE=innoDB DEFAULT charset=utf8;

INSERT INTO questao
(descricao, resposta, inform_adicionais, codigo_anamnese) values
('No momento, está sob tratamento médico?', 2, '', 1),
('Está tomando alguma medicação no momento?', 2, '', 1),
('Já sofreu alguma doença grave?', 2, '', 1),
('Já foi operado(a) alguma vez?', 2, '', 1),
('Vecê esteve sob tratamento de radioterapia ou quimioterapia?', 2, '', 1),
('Existe algum caso de diabetes, tuberculose ou câncer na família?', 2, '', 1),
('Já tomou anestesia local para tratar ou extrarir dentes?', 1, '', 1),
('Alguma vez tomou penicilina ou outro antibiótico?', 2, '', 1),
('Apresenta algum problema no coração utiliza Marca Passo ou Válvula Cardiaca Artificial?', 2, '', 1),
('Costuma ter pernas, pés e olhos inchados?', 2, '', 1),
('Sente falta de ar?', 2, '', 1),
('Já fez exame de sangue para verificação de doenças sexualmente transmissíveis(sifilis, gonorréia, Herpes, AIDS ou Hepatite)?', 1, '', 1),
('Sangra muito quando extrai dentes?', 2, '', 1),
('Tem algum outro problema que julgue importante?', 2, '', 1),
('Está grávida no momento?', 2, '', 1),
('É alérgico a algum medicamento?', 2, '', 1),
('É fumante ou ex-fumanete? Quantos cigarros cosumidos por dia?', 2, '', 1),
('Você tem algum problema renal, hepático?', 2, '', 1),
('Você tem hipertensão arterial(pressão alta)?', 2, '', 1),
('Já teve febre reumática?', 2, '', 1),
('Tem hábito de ranger ou apertar os dentes?', 2, '', 1),
('Você costuma escovar os dentes? Quantas vezes ao dia?', 1, '3 vezes', 1),
('Usa fio dental? Usa antiséptico bucal?', 2, '', 1),

('No momento, está sob tratamento médico?', 2,'', 2),
('Está tomando alguma medicação no momento?', 2,'', 2),
('Já sofreu alguma doença grave?', 2,'', 2),
('Já foi operado(a) alguma vez?', 2,'', 2),
('Vecê esteve sob tratamento de radioterapia ou quimioterapia?', 2,'', 2),
('Existe algum caso de diabetes, tuberculose ou câncer na família?', 2,'', 2),
('Já tomou anestesia local para tratar ou extrarir dentes?', 2,'', 2),
('Alguma vez tomou penicilina ou outro antibiótico?', 2,'', 2),
('Apresenta algum problema no coração utiliza Marca Passo ou Válvula Cardiaca Artificial?', 2,'', 2),
('Costuma ter pernas, pés e olhos inchados?', 2,'', 2),
('Sente falta de ar?', 2,'', 2),
('Já fez exame de sangue para verificação de doenças sexualmente transmissíveis(sifilis, gonorréia, Herpes, AIDS ou Hepatite)?', 1,'Nenhuma doença', 2),
('Sangra muito quando extrai dentes?', 2,'', 2),
('Tem algum outro problema que julgue importante?', 2,'', 2),
('Está grávida no momento?', 2,'', 2),
('É alérgico a algum medicamento?', 2,'', 2),
('É fumante ou ex-fumanete? Quantos cigarros cosumidos por dia?', 2,'', 2),
('Você tem algum problema renal, hepático?', 2,'', 2),
('Você tem hipertensão arterial(pressão alta)?', 2,'', 2),
('Já teve febre reumática?', 2,'', 2),
('Tem hábito de ranger ou apertar os dentes?', 2,'', 2),
('Você costuma escovar os dentes? Quantas vezes ao dia?', 1,'5 vezes', 2),
('Usa fio dental? Usa antiséptico bucal?', 2,'', 2),

('No momento, está sob tratamento médico?', 2,'', 3),
('Está tomando alguma medicação no momento?', 2,'', 3),
('Já sofreu alguma doença grave?', 2,'', 3),
('Já foi operado(a) alguma vez?', 2,'', 3),
('Vecê esteve sob tratamento de radioterapia ou quimioterapia?', 2,'', 3),
('Existe algum caso de diabetes, tuberculose ou câncer na família?', 2,'', 3),
('Já tomou anestesia local para tratar ou extrarir dentes?', 2,'', 3),
('Alguma vez tomou penicilina ou outro antibiótico?', 2,'', 3),
('Apresenta algum problema no coração utiliza Marca Passo ou Válvula Cardiaca Artificial?', 2,'', 3),
('Costuma ter pernas, pés e olhos inchados?', 2,'', 3),
('Sente falta de ar?', 2,'', 3),
('Já fez exame de sangue para verificação de doenças sexualmente transmissíveis(sifilis, gonorréia, Herpes, AIDS ou Hepatite)?', 2,'', 3),
('Sangra muito quando extrai dentes?', 2,'', 3),
('Tem algum outro problema que julgue importante?', 2,'', 3),
('Está grávida no momento?', 2,'', 3),
('É alérgico a algum medicamento?', 2,'', 3),
('É fumante ou ex-fumanete? Quantos cigarros cosumidos por dia?', 2,'', 3),
('Você tem algum problema renal, hepático?', 2,'', 3),
('Você tem hipertensão arterial(pressão alta)?', 2,'', 3),
('Já teve febre reumática?', 2,'', 3),
('Tem hábito de ranger ou apertar os dentes?', 2,'', 3),
('Você costuma escovar os dentes? Quantas vezes ao dia?', 2,'', 3),
('Usa fio dental? Usa antiséptico bucal?', 2,'', 3),

('No momento, está sob tratamento médico?', 2,'', 4),
('Está tomando alguma medicação no momento?', 2,'', 4),
('Já sofreu alguma doença grave?', 2,'', 4),
('Já foi operado(a) alguma vez?', 2,'', 4),
('Vecê esteve sob tratamento de radioterapia ou quimioterapia?', 2,'', 4),
('Existe algum caso de diabetes, tuberculose ou câncer na família?', 2,'', 4),
('Já tomou anestesia local para tratar ou extrarir dentes?', 2,'', 4),
('Alguma vez tomou penicilina ou outro antibiótico?', 2,'', 4),
('Apresenta algum problema no coração utiliza Marca Passo ou Válvula Cardiaca Artificial?', 2,'', 4),
('Costuma ter pernas, pés e olhos inchados?', 2,'', 4),
('Sente falta de ar?', 2,'', 4),
('Já fez exame de sangue para verificação de doenças sexualmente transmissíveis(sifilis, gonorréia, Herpes, AIDS ou Hepatite)?', 2,'', 4),
('Sangra muito quando extrai dentes?', 2,'', 4),
('Tem algum outro problema que julgue importante?', 2,'', 4),
('Está grávida no momento?', 2,'', 4),
('É alérgico a algum medicamento?', 2,'', 4),
('É fumante ou ex-fumanete? Quantos cigarros cosumidos por dia?', 2,'', 4),
('Você tem algum problema renal, hepático?', 2,'', 4),
('Você tem hipertensão arterial(pressão alta)?', 2,'', 4),
('Já teve febre reumática?', 2,'', 4),
('Tem hábito de ranger ou apertar os dentes?', 2,'', 4),
('Você costuma escovar os dentes? Quantas vezes ao dia?', 2,'', 4),
('Usa fio dental? Usa antiséptico bucal?', 2,'', 4),


('No momento, está sob tratamento médico?', 2,'', 5),
('Está tomando alguma medicação no momento?', 2,'', 5),
('Já sofreu alguma doença grave?', 2,'', 5),
('Já foi operado(a) alguma vez?', 2,'', 5),
('Vecê esteve sob tratamento de radioterapia ou quimioterapia?', 2,'', 5),
('Existe algum caso de diabetes, tuberculose ou câncer na família?', 2,'', 5),
('Já tomou anestesia local para tratar ou extrarir dentes?', 2,'', 5),
('Alguma vez tomou penicilina ou outro antibiótico?', 2,'', 5),
('Apresenta algum problema no coração utiliza Marca Passo ou Válvula Cardiaca Artificial?', 2,'', 5),
('Costuma ter pernas, pés e olhos inchados?', 2,'', 5),
('Sente falta de ar?', 2,'', 5),
('Já fez exame de sangue para verificação de doenças sexualmente transmissíveis(sifilis, gonorréia, Herpes, AIDS ou Hepatite)?', 2,'', 5),
('Sangra muito quando extrai dentes?', 2,'', 5),
('Tem algum outro problema que julgue importante?', 2,'', 5),
('Está grávida no momento?', 2,'', 5),
('É alérgico a algum medicamento?', 2,'', 5),
('É fumante ou ex-fumanete? Quantos cigarros cosumidos por dia?', 2,'', 5),
('Você tem algum problema renal, hepático?', 2,'', 5),
('Você tem hipertensão arterial(pressão alta)?', 2,'', 5),
('Já teve febre reumática?', 2,'', 5),
('Tem hábito de ranger ou apertar os dentes?', 2,'', 5),
('Você costuma escovar os dentes? Quantas vezes ao dia?', 1,'3 vezes', 5),
('Usa fio dental? Usa antiséptico bucal?', 1,'', 5);







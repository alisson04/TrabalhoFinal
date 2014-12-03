CREATE TABLE DEPARTAMENTO(
	ID INTEGER IDENTITY,
	CODIGO VARCHAR(3),
	NOME VARCHAR(150)
);

CREATE TABLE USUARIO(
	ID INTEGER IDENTITY,
	NOME VARCHAR(150),
	EMAIL VARCHAR(150),
	SENHA VARCHAR(150),
	CARGO VARCHAR(25),
	DEPARTAMENTO INTEGER,
	FOREIGN KEY(DEPARTAMENTO) REFERENCES DEPARTAMENTO(ID)
);


CREATE TABLE PROJETO(
	ID INTEGER IDENTITY,
	NOME VARCHAR(150),
	DESCRICAO VARCHAR(250),
	INICIO DATE,
	FIM DATE,
	DEPARTAMENTO INTEGER,
	FOREIGN KEY(DEPARTAMENTO) REFERENCES DEPARTAMENTO(ID)
);

CREATE TABLE ATIVIDADE(
	ID INTEGER IDENTITY,
	NOME VARCHAR(150),
	PROJETO INTEGER,
	ENCARREGADO INTEGER,
	DURACAOPREVISTA DOUBLE,
	TOTALHORAS DOUBLE,
	PERCENTUAL_CONCLUSAO DOUBLE,
	FOREIGN KEY (PROJETO) REFERENCES PROJETO(ID),
	FOREIGN KEY (ENCARREGADO) REFERENCES USUARIO(ID)
);
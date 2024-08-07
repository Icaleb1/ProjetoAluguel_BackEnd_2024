DROP DATABASE DB_CAMAX;

CREATE DATABASE IF NOT EXISTS DB_CAMAX;

USE DB_CAMAX;

CREATE TABLE USUARIO (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    SENHA VARCHAR(100) NOT NULL,
    CPF VARCHAR(11) NOT NULL,
    DATA_NASCIMENTO DATE NOT NULL,
    TELEFONE VARCHAR(100) NOT NULL,
    ADM BOOLEAN NOT NULL,
    ATIVO BOOLEAN NOT NULL,
    ID_SESSAO VARCHAR(255) NULL
);

CREATE TABLE BRINQUEDO (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(100) NOT NULL,
    DESCRICAO VARCHAR(150) NOT NULL, 
    ESTOQUE_DISPONIVEL INT,
    ESTOQUE_TOTAL INT,
    VALOR_DIARIA DOUBLE NOT NULL
);

CREATE TABLE ENDERECO (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(100) NOT NULL,
	ID_USUARIO INT NOT NULL,
    CEP INT NOT NULL,
	ESTADO VARCHAR(100) NOT NULL,
    CIDADE VARCHAR(100) NOT NULL,
    BAIRRO VARCHAR(100) NOT NULL,
    LOTE VARCHAR(100) NOT NULL,
    NUMERO INT NOT NULL,
    COMPLEMENTO VARCHAR(100),
    REFERENCIAS VARCHAR(150),
	FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)
);

CREATE TABLE ALUGUEL (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ID_USUARIO INT NOT NULL,
    ID_ENDERECO INT,
    DATA_ALUGUEL DATE NOT NULL,
    DATA_DEVOLUCAO DATE NOT NULL,
    DATA_DEVOLUCAO_DEFINITIVA DATE NOT NULL,
	VALORES_ADICIONAIS DOUBLE,
    VALOR_TOTAL DOUBLE NOT NULL,
    FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)
);

CREATE TABLE FRETE (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ID_ALUGUEL INT NOT NULL,
    DISTANCIA DOUBLE NOT NULL,
    VALOR DOUBLE NOT NULL,
	FOREIGN KEY (ID_ALUGUEL) REFERENCES ALUGUEL(ID)
);

CREATE TABLE ITEM (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ID_ALUGUEL INT,
    ID_BRINQUEDO INT NOT NULL,
    DISPONIVEL BOOLEAN NOT NULL,
    FOREIGN KEY (ID_BRINQUEDO ) REFERENCES BRINQUEDO(ID)
);

CREATE TABLE CARRINHO (
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ID_USUARIO INT NOT NULL,
    DATA_CRIACAO DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    DATA_FINALIZACAO DATETIME,
    FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)
);

CREATE TABLE ITEM_CARRINHO (
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ID_CARRINHO INT NOT NULL,
    ID_BRINQUEDO INT NOT NULL,
    QUANTIDADE INT NOT NULL,
    FOREIGN KEY (ID_CARRINHO) REFERENCES CARRINHO(ID),
    FOREIGN KEY (ID_BRINQUEDO) REFERENCES BRINQUEDO(ID)
);




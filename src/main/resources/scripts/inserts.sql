USE DB_CAMAX;

-- INSERT DE USUARIOS

INSERT INTO usuario (nome, email, senha, cpf, data_nascimento, telefone, adm, ativo)
VALUES('caleb', 'caleb@', '1234', '11122211121', '2004-03-12', 48965321566, true, true);

INSERT INTO usuario (nome, email, senha, cpf, data_nascimento, telefone, adm, ativo)
VALUES('Ana', 'ana@gmail.com', 'senha123', '12345678901', '1990-01-15', 48999999998, false, true);

INSERT INTO usuario (nome, email, senha, cpf, data_nascimento, telefone, adm, ativo)
VALUES('Bruno', 'bruno@gmail.com', 'senha123', '23456789012', '1985-02-20', 48988888887, false, true);

INSERT INTO usuario (nome, email, senha, cpf, data_nascimento, telefone, adm, ativo)
VALUES('Carlos', 'carlos@gmail.com', 'senha123', '34567890123', '1992-03-25', 48977777776, false, false);

INSERT INTO usuario (nome, email, senha, cpf, data_nascimento, telefone, adm, ativo)
VALUES('Daniela', 'daniela@gmail.com', 'senha123', '45678901234', '1988-04-30', 48966666665, false, true);

INSERT INTO usuario (nome, email, senha, cpf, data_nascimento, telefone, adm, ativo)
VALUES('Eduardo', 'eduardo@gmail.com', 'senha123', '56789012345', '1995-05-05', 48955555554, false, true);

INSERT INTO usuario (nome, email, senha, cpf, data_nascimento, telefone, adm, ativo)
VALUES('Fernanda', 'fernanda@gmail.com', 'senha123', '67890123456', '1983-06-10', 48944444443, false, false);

INSERT INTO usuario (nome, email, senha, cpf, data_nascimento, telefone, adm, ativo)
VALUES('Gustavo', 'gustavo@gmail.com', 'senha123', '78901234567', '1991-07-15', 48933333332, false, true);

INSERT INTO usuario (nome, email, senha, cpf, data_nascimento, telefone, adm, ativo)
VALUES('Helena', 'helena@gmail.com', 'senha123', '89012345678', '1987-08-20', 48922222221, false, true);

INSERT INTO usuario (nome, email, senha, cpf, data_nascimento, telefone, adm, ativo)
VALUES('Igor', 'igor@gmail.com', 'senha123', '90123456789', '1993-09-25', 48911111110, false, false);

-- INSERT DE BRINQUEDOS

INSERT INTO BRINQUEDO (NOME, DESCRICAO, QTD_EM_ESTOQUE, VALOR_DIARIA)
VALUES ('Pula-Pula', 'Pula-Pula inflável para até 5 crianças. Suporta um peso total de até 360 kg. Ideal para festas infantis.', 10, 199.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, QTD_EM_ESTOQUE, VALOR_DIARIA)
VALUES ('Tobogã Inflável', 'Tobogã inflável com escorregador e piscina de bolinhas. Capacidade para 2 crianças por vez. Peso máximo por criança: 65 kg.', 10, 199.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, QTD_EM_ESTOQUE, VALOR_DIARIA)
VALUES ('Piscina de Bolinhas', 'Piscina de bolinhas com 2,5 metros de diâmetro e capacidade para até 3 crianças. Ideal para festas e eventos.', 10, 149.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, QTD_EM_ESTOQUE, VALOR_DIARIA)
VALUES ('Piscina de Espuma', 'Piscina de espuma para crianças se divertirem, suporta até 4 crianças por vez. Ideal para festas de verão.', 10, 149.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, QTD_EM_ESTOQUE, VALOR_DIARIA)
VALUES ('Escorregador Infantil', 'Escorregador colorido para crianças, suporta até 1 crianças de até 60 kg cada. Divertido e seguro.', 10, 99.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, QTD_EM_ESTOQUE, VALOR_DIARIA)
VALUES ('Futebol de Sabão', 'Campo inflável de futebol com sabão, ideal para brincadeiras em festas. Suporta até 10 jogadores por time. Tamanho aproximado de 10m x 5m.', 10, 249.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, QTD_EM_ESTOQUE, VALOR_DIARIA)
VALUES ('Tombo Legal', 'Jogo de diversão onde o participante deve acertar um alvo para fazer com que uma cadeira se incline e "tome um tombo". Tamanho aproximado de 2m x 2m.', 3, 149.99);

-- INSERTS DO ENDEREÇO

INSERT INTO ENDERECO (NOME, PRINCIPAL, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa', true, 1, 88001000, 'SC', 'Florianópolis', 'Centro', 'Quadra 1', 123, 'Apto 101', 'Próximo à Praça XV.');

INSERT INTO ENDERECO (NOME, PRINCIPAL, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('trabalho', false, 2, 88002000, 'SC', 'Florianópolis', 'Beira-Mar', 'Loteamento 2', 456, 'Casa', 'Perto do Shopping Beira-Mar.');

INSERT INTO ENDERECO (NOME, PRINCIPAL, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('trabalho', false, 3, 88003000, 'SC', 'Florianópolis', 'Trindade', 'Lote 3', 789, 'Bloco B', 'Ao lado da UFSC.');

INSERT INTO ENDERECO (NOME, PRINCIPAL, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa', false, 4, 88004000, 'SC', 'Florianópolis', 'Pantanal', 'Loteamento 4', 1011, 'Apto 202', 'Próximo à UDESC.');

INSERT INTO ENDERECO (NOME, PRINCIPAL, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa', false, 5, 88005000, 'SC', 'Florianópolis', 'Coqueiros', 'Quadra 5', 1213, 'Casa Amarela', 'Em frente à Praia do Meio.');

INSERT INTO ENDERECO (NOME, PRINCIPAL, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('prédio', false, 6, 88006000, 'SC', 'Florianópolis', 'Jurerê', 'Lote 6', 1415, 'Apto 303', 'Condomínio Fechado.');

INSERT INTO ENDERECO (NOME, PRINCIPAL, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa dos pais', false, 7, 88007000, 'SC', 'Florianópolis', 'Ingleses', 'Loteamento 7', 1617, 'Casa 1', 'Rua da Praia.');

INSERT INTO ENDERECO (NOME, PRINCIPAL, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa de amigo', false, 8, 88008000, 'SC', 'Florianópolis', 'Campeche', 'Quadra 8', 1819, 'Bloco C', 'Próximo ao Aeroporto.');

INSERT INTO ENDERECO (NOME, PRINCIPAL, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa', false, 9, 88009000, 'SC', 'Florianópolis', 'Canasvieiras', 'Lote 9', 2021, 'Casa 2', 'Perto do Supermercado.');

INSERT INTO ENDERECO (NOME, PRINCIPAL, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa', false, 10, 88010000, 'SC', 'Florianópolis', 'Agronômica', 'Loteamento 10', 2223, 'Apto 404', 'Ao lado do Parque.');

-- INSERTS DE FRETE

INSERT INTO FRETE (VALOR) VALUES (20.00);
INSERT INTO FRETE (VALOR) VALUES (50.00);
INSERT INTO FRETE (VALOR) VALUES (100.00);

-- INSERT DE ALUGUEL

INSERT INTO ALUGUEL (ID_USUARIO, ID_FRETE, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, DISTANCIA, VALOR_TOTAL)
VALUES (1, 1, '2024-06-01', '2024-06-03', '2024-06-03', 23, 419.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_FRETE, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, DISTANCIA, VALOR_TOTAL)
VALUES (2, 2, '2024-06-02', '2024-06-04', '2024-06-04', 24, 464.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_FRETE, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, DISTANCIA, VALOR_TOTAL)
VALUES (3, 3, '2024-06-03', '2024-06-05', '2024-06-05', 54, 319.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_FRETE, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, DISTANCIA, VALOR_TOTAL)
VALUES (4, 1, '2024-06-04', '2024-06-06', '2024-06-06', 65, 379.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_FRETE, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, DISTANCIA, VALOR_TOTAL)
VALUES (5, 2, '2024-06-05', '2024-06-07', '2024-06-07', 21, 314.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_FRETE, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, DISTANCIA, VALOR_TOTAL)
VALUES (6, 3, '2024-06-06', '2024-06-08', '2024-06-08', 12, 239.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_FRETE, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, DISTANCIA, VALOR_TOTAL)
VALUES (7, 1, '2024-06-07', '2024-06-09', '2024-06-09', 87, 299.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_FRETE, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, DISTANCIA, VALOR_TOTAL)
VALUES (8, 2, '2024-06-08', '2024-06-10', '2024-06-10', 34, 359.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_FRETE, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, DISTANCIA, VALOR_TOTAL)
VALUES (9, 3, '2024-06-09', '2024-06-11', '2024-06-11', 54, 264.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_FRETE, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, DISTANCIA, VALOR_TOTAL)
VALUES (10, 1, '2024-06-10', '2024-06-12', '2024-06-12,', 54, 424.98);

-- INSERT DE ITENS

-- BRINQUEDO 1 - PULA-PULA
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, ALUGADO)
VALUES (1, 1, false);
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, ALUGADO)
VALUES (1, 1, true);


-- BRINQUEDO 2 - TOBOGÃ INFLÁVEL
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, ALUGADO)
VALUES (2, 2, false);

-- BRINQUEDO 3 - PISCINA DE BOLINHAS
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, ALUGADO)
VALUES (3, 3, false);

-- BRINQUEDO 4 - PISCINA DE ESPUMA
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, ALUGADO)
VALUES (4, 4, false);

-- BRINQUEDO 5 - ESCORREGADOR INFANTIL
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, ALUGADO)
VALUES (5, 5, false);

-- BRINQUEDO 6 - FUTEBOL DE SABÃO
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, ALUGADO)
VALUES (6, 6, false);

-- BRINQUEDO 7 - TOMBO LEGAL
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, ALUGADO)
VALUES (7, 7, false);

INSERT INTO CARRINHO(ID_USUARIO) VALUES (1);

SELECT * FROM USUARIO;

SELECT * FROM BRINQUEDO;

SELECT * FROM ENDERECO;

SELECT * FROM FRETE;

SELECT * FROM ALUGUEL;

SELECT * FROM ITEM;

select * from carrinho;

select * from item_Carrinho;


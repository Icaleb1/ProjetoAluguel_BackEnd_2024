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

INSERT INTO BRINQUEDO (NOME, DESCRICAO, VALOR_DIARIA)
VALUES ('Pula-Pula', 'Pula-Pula inflável para até 5 crianças. Suporta um peso total de até 360 kg. Ideal para festas infantis.', 199.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, VALOR_DIARIA)
VALUES ('Tobogã Inflável', 'Tobogã inflável com escorregador e piscina de bolinhas. Capacidade para 2 crianças por vez. Peso máximo por criança: 65 kg.', 199.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, VALOR_DIARIA)
VALUES ('Piscina de Bolinhas', 'Piscina de bolinhas com 2,5 metros de diâmetro e capacidade para até 3 crianças. Ideal para festas e eventos.', 149.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, VALOR_DIARIA)
VALUES ('Piscina de Espuma', 'Piscina de espuma para crianças se divertirem, suporta até 4 crianças por vez. Ideal para festas de verão.', 149.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, VALOR_DIARIA)
VALUES ('Escorregador Infantil', 'Escorregador colorido para crianças, suporta até 1 crianças de até 60 kg cada. Divertido e seguro.', 99.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, VALOR_DIARIA)
VALUES ('Futebol de Sabão', 'Campo inflável de futebol com sabão, ideal para brincadeiras em festas. Suporta até 10 jogadores por time. Tamanho aproximado de 10m x 5m.', 249.99);

INSERT INTO BRINQUEDO (NOME, DESCRICAO, VALOR_DIARIA)
VALUES ('Tombo Legal', 'Jogo de diversão onde o participante deve acertar um alvo para fazer com que uma cadeira se incline e "tome um tombo". Tamanho aproximado de 2m x 2m.', 149.99);

-- INSERTS DO ENDEREÇO

INSERT INTO ENDERECO (NOME, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa', 1, 88001000, 'SC', 'Florianópolis', 'Centro', 'Quadra 1', 123, 'Apto 101', 'Próximo à Praça XV.');

INSERT INTO ENDERECO (NOME, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('trabalho', 2, 88002000, 'SC', 'Florianópolis', 'Beira-Mar', 'Loteamento 2', 456, 'Casa', 'Perto do Shopping Beira-Mar.');

INSERT INTO ENDERECO (NOME, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('trabalho', 3, 88003000, 'SC', 'Florianópolis', 'Trindade', 'Lote 3', 789, 'Bloco B', 'Ao lado da UFSC.');

INSERT INTO ENDERECO (NOME, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa', 4, 88004000, 'SC', 'Florianópolis', 'Pantanal', 'Loteamento 4', 1011, 'Apto 202', 'Próximo à UDESC.');

INSERT INTO ENDERECO (NOME, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa', 5, 88005000, 'SC', 'Florianópolis', 'Coqueiros', 'Quadra 5', 1213, 'Casa Amarela', 'Em frente à Praia do Meio.');

INSERT INTO ENDERECO (NOME, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('prédio', 6, 88006000, 'SC', 'Florianópolis', 'Jurerê', 'Lote 6', 1415, 'Apto 303', 'Condomínio Fechado.');

INSERT INTO ENDERECO (NOME, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa dos pais', 7, 88007000, 'SC', 'Florianópolis', 'Ingleses', 'Loteamento 7', 1617, 'Casa 1', 'Rua da Praia.');

INSERT INTO ENDERECO (NOME, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa de amigo', 8, 88008000, 'SC', 'Florianópolis', 'Campeche', 'Quadra 8', 1819, 'Bloco C', 'Próximo ao Aeroporto.');

INSERT INTO ENDERECO (NOME, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa', 9, 88009000, 'SC', 'Florianópolis', 'Canasvieiras', 'Lote 9', 2021, 'Casa 2', 'Perto do Supermercado.');

INSERT INTO ENDERECO (NOME, ID_USUARIO, CEP, ESTADO, CIDADE, BAIRRO, LOTE, NUMERO, COMPLEMENTO, REFERENCIAS)
VALUES ('casa', 10, 88010000, 'SC', 'Florianópolis', 'Agronômica', 'Loteamento 10', 2223, 'Apto 404', 'Ao lado do Parque.');


-- INSERT DE ALUGUEL

INSERT INTO ALUGUEL (ID_USUARIO, ID_ENDERECO, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, VALOR_TOTAL)
VALUES (1, 1, '2024-06-01', '2024-06-03', '2024-06-03', 419.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_ENDERECO, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, VALOR_TOTAL)
VALUES (2, 2,  '2024-06-02', '2024-06-04', '2024-06-04', 464.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_ENDERECO, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, VALOR_TOTAL)
VALUES (3, 3, '2024-06-03', '2024-06-05', '2024-06-05', 319.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_ENDERECO, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, VALOR_TOTAL)
VALUES (4, 1,  '2024-06-04', '2024-06-06', '2024-06-06', 379.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_ENDERECO, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, VALOR_TOTAL)
VALUES (5, 2,  '2024-06-05', '2024-06-07', '2024-06-07', 314.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_ENDERECO, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, VALOR_TOTAL)
VALUES (6, 3, '2024-06-06', '2024-06-08', '2024-06-08', 239.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_ENDERECO, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, VALOR_TOTAL)
VALUES (7, 1, '2024-06-07', '2024-06-09', '2024-06-09', 299.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_ENDERECO, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, VALOR_TOTAL)
VALUES (8, 2, '2024-06-08', '2024-06-10', '2024-06-10', 359.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_ENDERECO, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, VALOR_TOTAL)
VALUES (9, 3,  '2024-06-09', '2024-06-11', '2024-06-11', 264.98);

INSERT INTO ALUGUEL (ID_USUARIO, ID_ENDERECO, DATA_ALUGUEL, DATA_DEVOLUCAO, DATA_DEVOLUCAO_DEFINITIVA, VALOR_TOTAL)
VALUES (10, 1,  '2024-06-10', '2024-06-12', '2024-06-12,', 424.98);

-- INSERTS DE FRETE

-- INSERT INTO FRETE (ID_ALUGUEL,DISTANCIA,VALOR) VALUES (1, 20, 20.00);
-- INSERT INTO FRETE (ID_ALUGUEL,DISTANCIA,VALOR) VALUES (1, 120, 50.00);
-- INSERT INTO FRETE (ID_ALUGUEL,DISTANCIA,VALOR) VALUES (2, 120, 100.00);


-- INSERT DE ITENS

-- BRINQUEDO 1 - PULA-PULA
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, DISPONIVEL)
VALUES (1, 1, true);

-- BRINQUEDO 2 - TOBOGÃ INFLÁVEL
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, DISPONIVEL)
VALUES (2, 2, true);

-- BRINQUEDO 3 - PISCINA DE BOLINHAS
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, DISPONIVEL)
VALUES (3, 3, true);

-- BRINQUEDO 4 - PISCINA DE ESPUMA
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, DISPONIVEL)
VALUES (4, 4, true);

-- BRINQUEDO 5 - ESCORREGADOR INFANTIL
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, DISPONIVEL)
VALUES (5, 5, true);

-- BRINQUEDO 6 - FUTEBOL DE SABÃO
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, DISPONIVEL)
VALUES (6, 6, true);

-- BRINQUEDO 7 - TOMBO LEGAL
INSERT INTO ITEM (ID_ALUGUEL, ID_BRINQUEDO, DISPONIVEL)
VALUES (7, 7, true);

INSERT INTO CARRINHO(ID_USUARIO) VALUES (1);

SELECT * FROM USUARIO;

SELECT * FROM BRINQUEDO;

SELECT * FROM ENDERECO;

SELECT * FROM FRETE;

SELECT * FROM ALUGUEL where id_usuario = 21;	

SELECT * FROM ITEM where id_brinquedo = 1;

select * from carrinho;

select * from item_Carrinho
where id_carrinho = 1;





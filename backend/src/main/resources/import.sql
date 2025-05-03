INSERT INTO time_furia (id, nome) VALUES (1, 'Counter-Strike 2');
INSERT INTO time_furia (id, nome) VALUES (2, 'Valorant');
INSERT INTO time_furia (id, nome) VALUES (3, 'Furia FC (Fut7)');
INSERT INTO time_furia (id, nome) VALUES (4, 'League of Legends');

INSERT INTO usuario (nome, data_nascimento, regiao, rede_social) VALUES ('Vitório', '2003-12-02', 'Brasília', '@vitorio.mic');
INSERT INTO usuario (nome, data_nascimento, regiao, rede_social) VALUES ('Ana', '2002-03-15', 'Minas Gerais', '@aninha076');

INSERT INTO usuario_times_favoritos (usuario_id, times_furia_id) VALUES (1, 1);
INSERT INTO usuario_times_favoritos (usuario_id, times_furia_id) VALUES (1, 2);
INSERT INTO usuario_times_favoritos (usuario_id, times_furia_id) VALUES (2, 3);

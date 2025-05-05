INSERT INTO time_furia (id, nome) VALUES (1, 'Counter-Strike 2');
INSERT INTO time_furia (id, nome) VALUES (2, 'Valorant');
INSERT INTO time_furia (id, nome) VALUES (3, 'Furia FC (Fut7)');
INSERT INTO time_furia (id, nome) VALUES (4, 'League of Legends');

INSERT INTO usuario (nome, data_nascimento, regiao, rede_social, email, senha) VALUES ('Vitório', '2003-12-02', 'Distrito Federal', '@vitorio.mic','vitorio.mic@exemple.com','$2a$12$Z5Ts53bnpJR8U4a3SqZ89uSsHqQ3.EZvQQikvIy4rSMx.nmEOFBJe');
INSERT INTO usuario (nome, data_nascimento, regiao, rede_social, email, senha) VALUES ('Ana', '2002-03-15', 'Minas Gerais', '@aninha076', 'aninha076@exemple.com','$2a$12$HDbFJPwcvx.OoJtygzEVNOBdxm4BkM5s2SSCIMfUqo5l6nChaIV6O');

INSERT INTO usuario_times_favoritos (usuario_id, times_furia_id) VALUES (1, 1);
INSERT INTO usuario_times_favoritos (usuario_id, times_furia_id) VALUES (1, 2);
INSERT INTO usuario_times_favoritos (usuario_id, times_furia_id) VALUES (2, 3);

INSERT INTO jogo (time_furia_id, adversario, data_hora, campeonato) VALUES (1, 'NAVI', '2025-05-04 18:00:00', 'IEM Dallas');

INSERT INTO jogo (time_furia_id, adversario, data_hora, campeonato) VALUES (2, 'LOUD', '2025-05-05 20:30:00', 'VCT Americas');

INSERT INTO jogo (time_furia_id, adversario, data_hora, campeonato) VALUES (3, 'Aniquiladores', '2025-05-06 19:00:00', 'Kings League 2025');
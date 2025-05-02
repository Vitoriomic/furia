INSERT INTO time_furia (id, nome) VALUES
(1, 'Counter-Strike 2'),
(2, 'Valorant'),
(3, 'Furia FC (Fut7)'),
(4, 'League of Legends');

INSERT INTO usuario (id, nome, data_nascimento, regiao, rede_social)
VALUES
(1, 'Vitório', '2003-02-12', 'Brasília', '@vitorio.mic'),
(2, 'Ana', '2002-03-15', 'Minas Gerais', '@aninha076');

INSERT INTO usuario_times_favoritos (usuario_id, times_furia_id) VALUES
(1, 1),
(1, 2),
(2, 3);

INSERT INTO jogo (id, time_furia_id, adversario, data_hora, campeonato) VALUES
(1, 1, 'NAVI', '2025-05-04 18:00:00', 'IEM Dallas'),
(2, 2, 'LOUD', '2025-05-05 20:30:00', 'VCT Americas'),
(3, 3, 'Aniquiladores', '2025-05-06 19:00:00', 'Kings League 2025');
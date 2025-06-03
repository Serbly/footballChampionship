INSERT INTO coaches (name, nationality, birthdate) VALUES
('Paulo Fonseca', 'Portugal', '1975-04-23'),
('Luis Enrique', 'Spain', '1970-11-15');

INSERT INTO teams (name, city, stadium, coach_id) VALUES
('OL', 'Lion', 'Groupama stadium', 1),
('PSG', 'Paris', 'Parc des Princes', 2);

INSERT INTO championships (name, season) VALUES
('Ligue 1', '24/25'),
('EPL', '24/25');

INSERT INTO matches (championship_id, date, home_team_id, away_team_id, stadium, score) VALUES
(1, '2025-03-21', 1, 2, 'Groupama stadium', '2:1'),
(1, '2025-03-25', 2, 1, 'Parc des Princes', '1:1');

INSERT INTO players (name, number, position, team_id, nationality, birthdate) VALUES
('Rayan Cherki', 18, 'FORWARD', 1, 'France', '2000-05-15'),
('Alexander Lacazette', 9, 'FORWARD', 1, 'France', '1991-03-22'),
('Bradley Barcola', 11, 'FORWARD', 2, 'France', '1998-06-12');

INSERT INTO goals (championship_id, match_id, player_id, minute, type) VALUES
(1 ,1, 1, 15, 'DEFAULT'),
(1,1, 2, 40, 'PENALTY'),
(1 ,1, 3, 80, 'DEFAULT'),
(1, 2, 2, 15, 'DEFAULT'),
(1, 2, 3, 40, 'DEFAULT');

INSERT INTO standings (championship_id, matches_played, wins, draws, losses, points, team_id) VALUES
(1, 2, 1, 1, 0, 4, 1),
(1, 2, 0, 1, 1, 1, 2);

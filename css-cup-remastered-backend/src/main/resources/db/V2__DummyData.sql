INSERT INTO discipline (name)
VALUES ('FOOTBALL'),
       ('VOLLEYBALL'),
       ('UNDECIDED');

INSERT INTO rolle (name)
VALUES ('ADMIN'),
       ('CAPTAIN'),
       ('PARTICIPANT'),
       ('UNDECIDED');

INSERT INTO team (name, team_discipline_id)
VALUES
       ('SCK', 1),
       ('FCL', 1),
       ('FCB', 2);

INSERT INTO player (firstname, lastname, function, email, password, player_discipline_id, player_role_id, player_team_id)
VALUES ('Philip', 'Baumann', 'IEL', 'philip.baumann@css.ch', '', 1, 2, 1),
       ('Leo', 'Scherer', 'IEE', 'leo.scherer@css.ch', '', 1, 3, 1),
       ('Daniel', 'Polgar', 'IEL', 'daniel.polgar@css.ch', '', 1, 2, 2),
       ('Jeffrey', 'Jefferson', 'IEB', 'jeffrey.jefferson@css.ch', '', 1, 3, 2),
       ('Berta', 'Brecht', 'IEC', 'berta.brecht@css.ch', '', 1, 2, 3),
       ('Charles', 'Charlson', 'HF', 'charles.charlson@css.ch', '', 1, 1, null),
       ('Dave', 'Davison', 'CH', 'dave.davison@css.ch', '', 1, 1, null),
       ('Elen', 'Elch', 'HFE', 'elen.elch@css.ch', '', 1, 1, null),
       ('Franz', 'Ferguson', 'LKB', 'franz.ferguson@css.ch', '', 1, 1, null)


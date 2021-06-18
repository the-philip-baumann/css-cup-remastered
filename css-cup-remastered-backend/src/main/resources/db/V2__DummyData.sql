INSERT INTO discipline (name)
VALUES ('FOOTBALL'),
       ('VOLLEYBALL'),
       ('UNDECIDED');

INSERT INTO rolle (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_CAPTAIN'),
       ('ROLE_PARTICIPANT'),
       ('ROLE_UNDECIDED');

INSERT INTO team (name, team_discipline_id)
VALUES
       ('SCK', 1),
       ('FCL', 1),
       ('FCB', 2);

INSERT INTO player (firstname, lastname, function, email, password, player_discipline_id, player_role_id, player_team_id, enabled)
VALUES ('Philip', 'Baumann', 'IEL', 'philip.baumann@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC ', 1, 2, 1, true),
       ('Leo', 'Scherer', 'IEE', 'leo.scherer@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC ', 1, 3, 1, true),
       ('Daniel', 'Polgar', 'IEL', 'daniel.polgar@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC ', 1, 2, 2, true),
       ('Jeffrey', 'Jefferson', 'IEB', 'jeffrey.jefferson@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC ', 1, 3, 2, true),
       ('Berta', 'Brecht', 'IEC', 'berta.brecht@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC ', 1, 2, 3, true),
       ('Charles', 'Charlson', 'HF', 'charles.charlson@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC ', 1, 1, null, true),
       ('Dave', 'Davison', 'CH', 'dave.davison@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC ', 1, 1, null, true),
       ('Elen', 'Elch', 'HFE', 'elen.elch@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC ', 1, 1, null, true),
       ('Franz', 'Ferguson', 'LKB', 'franz.ferguson@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC ', 1, 1, null, true),
       ('admin', 'admin', 'HOQ', 'admin.admin@css.ch', '$2a$10$vr3Umdcaoy5nDLOw20bPBeUxlI1Jxp2RKXL3vI1S5tpi0Z/choxTW', 1, 1, null, true),
       ('captain', 'captain', 'HOQ', 'captain@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC', 1, 2, null, true),
       ('participant', 'participant', 'HOQ', 'participant@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC', 3, 1, null, true),
       ('undecided', 'undecided', 'HOQ', 'undecided@css.ch', '$2y$12$gLNICNJNji3zqefTazxsmetWOB4brAMiQPv4YcGaf9LLng/fleSJC', 1, 4, null, true);


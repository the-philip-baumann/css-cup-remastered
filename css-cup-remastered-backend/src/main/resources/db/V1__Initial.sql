CREATE TABLE role
(
    id   SERIAL      NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE discipline
(
    id   SERIAL      NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE player
(
    id         SERIAL       NOT NULL PRIMARY KEY,
    firstname  VARCHAR(50)  NOT NULL,
    lastname   VARCHAR(50)  NOT NULL,
    function   VARCHAR(50)  NOT NULL,
    email      VARCHAR(100) NOT NULL,
    password   bytea        NOT NULL,
    role       INT          NOT NULL,
    discipline INT          NOT NULL,
    FOREIGN KEY (id) REFERENCES role(id),
    FOREIGN KEY (id) REFERENCES discipline(id)
);



CREATE TABLE team
(
    id         SERIAL      NOT NULL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    discipline INT         NOT NULL,
    captain    INT         NOT NULL,
    FOREIGN KEY (discipline) REFERENCES discipline (id),
    FOREIGN KEY (captain) REFERENCES player(id)
);



INSERT INTO discipline (name)
VALUES ('football'),
       ('volleyball');

INSERT INTO role (name) VALUES ('ADMIN'), ('CAPTAIN'), ('PARTICIPANT'), ('UNDECIDED');

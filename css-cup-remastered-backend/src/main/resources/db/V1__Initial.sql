CREATE TABLE public.rolle
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT rolle_pkey PRIMARY KEY (id)
);

CREATE TABLE public.discipline
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT discipline_pkey PRIMARY KEY (id)
);

CREATE TABLE public.team
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default" UNIQUE,
    team_discipline_id bigint,
    CONSTRAINT team_pkey PRIMARY KEY (id),
    CONSTRAINT fknax39c8v433mhak5liy0fqxd8 FOREIGN KEY (team_discipline_id)
        REFERENCES public.discipline (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.player
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    firstname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    function character varying(255) COLLATE pg_catalog."default" NOT NULL,
    lastname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) NOT NULL,
    enabled boolean NOT NULL,
    player_discipline_id bigint,
    player_role_id bigint,
    player_team_id bigint,
    CONSTRAINT player_pkey PRIMARY KEY (id),
    CONSTRAINT fk1st34erb4epwshpsdc7l91elx FOREIGN KEY (player_team_id)
        REFERENCES public.team (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk4qfv0f9p44wc3g129ome4hg1c FOREIGN KEY (player_role_id)
        REFERENCES public.rolle (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkagjq6u83b4jfv5oy8ov26d8px FOREIGN KEY (player_discipline_id)
        REFERENCES public.discipline (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

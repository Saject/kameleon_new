CREATE TABLE public.account (
                                id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                                login varchar NOT NULL,
                                "password" varchar NULL,
                                created timestamp NULL,
                                updated timestamp NULL,
                                CONSTRAINT account_pk PRIMARY KEY (id)
);
CREATE TABLE public.profile_info (
                                     id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                                     firstname varchar NULL,
                                     lastname varchar NULL,
                                     surname varchar NULL,
                                     created timestamp NULL,
                                     updated timestamp NULL,
                                     account_id int8 NOT NULL,
                                     email varchar NULL,
                                     tel varchar NULL,
                                     CONSTRAINT account_info_un UNIQUE (account_id),
                                     CONSTRAINT person_pk PRIMARY KEY (id),
                                     CONSTRAINT person_fk FOREIGN KEY (account_id) REFERENCES public.account(id)
);

CREATE TABLE public."quote" (
                                id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                                created timestamp NULL,
                                updated timestamp NULL,
                                profileinfo_id int8 NULL,
                                score_id int8 NULL,
                                note varchar NULL,
                                CONSTRAINT quote_pk PRIMARY KEY (id),
                                CONSTRAINT quote_fk FOREIGN KEY (profileinfo_id) REFERENCES public.profile_info(id)
);
CREATE TABLE public.score (
                              created timestamp NULL,
                              id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                              updated timestamp NULL,
                              profile_id int8 NULL,
                              quote_id int8 NOT NULL,
                              CONSTRAINT score_pk PRIMARY KEY (id),
                              CONSTRAINT score_fk FOREIGN KEY (quote_id) REFERENCES public."quote"(id),
                              CONSTRAINT score_profile_fk FOREIGN KEY (profile_id) REFERENCES public.profile_info(id) ON DELETE CASCADE
);






CREATE SEQUENCE tbaccount_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
CREATE TABLE public.tbaccount (
    id bigint NOT NULL DEFAULT NEXTVAL('tbaccount_seq'),
    account CHARACTER VARYING(3) not null
);

ALTER TABLE tbaccount ADD CONSTRAINT pk_tbaccount PRIMARY KEY (id);


CREATE SEQUENCE tbtransaction_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
CREATE TABLE public.tbtransaction (
    id bigint NOT NULL DEFAULT NEXTVAL('tbtransaction_seq'),
    total_amount double precision not null,
    mcc CHARACTER VARYING(10) not null,
    merchant CHARACTER VARYING(60) not null,
    id_account bigint not null

);

ALTER TABLE tbtransaction ADD CONSTRAINT pk_tbtransaction PRIMARY KEY (id);
ALTER TABLE tbtransaction ADD CONSTRAINT fk_tbtransaction_tbaccount FOREIGN KEY (id_account) REFERENCES tbaccount(id);


CREATE SEQUENCE tbbalance_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
CREATE TABLE public.tbbalance (
    id bigint NOT NULL DEFAULT NEXTVAL('tbbalance_seq'),
    amount double precision not null,
    classificacao CHARACTER VARYING(4) not null,
    id_account bigint not null

);

ALTER TABLE tbbalance ADD CONSTRAINT pk_tbbalance PRIMARY KEY (id);
ALTER TABLE tbbalance ADD CONSTRAINT fk_tbbalance_tbaccount FOREIGN KEY (id_account) REFERENCES tbaccount(id);
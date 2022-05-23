
/* Create the schema heritage if not available*/
CREATE SCHEMA IF NOT EXISTS heritage;

/* User table */
CREATE TABLE IF NOT EXISTS User
(
    id bigint NOT NULL,
    first_name character varying(100),
    last_name character varying(100),
    user_name character varying(100),
    password character varying(100),
    active "char",
    "isAdmin" "char",
    PRIMARY KEY (id)
    );



CREATE TABLE IF NOT EXISTS Account
(
    id bigint NOT NULL,
    account_number bigint,
    account_type character varying(15),
    net_banking_enabled "char",
    debit_card_status "char",
    balance double precision,
    available_balance double precision,
    over_draft_balance double precision,
    active "char",
    user_id bigint,
    PRIMARY KEY (id),
    CONSTRAINT user_account_fk FOREIGN KEY (user_id)
    REFERENCES User (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    );
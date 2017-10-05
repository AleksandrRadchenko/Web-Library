CREATE SEQUENCE user_id_seq;

CREATE SEQUENCE order_id_seq;

CREATE SEQUENCE book_id_seq;

CREATE SEQUENCE book_order_id_seq;

CREATE SEQUENCE edition_id_seq;

-- create type role as enum ('reader', 'librarian')
-- ;
--
-- create type status as enum ('NEW', 'IN_PROGRESS', 'CLOSED')
-- ;
--
-- create type option as enum ('SUBSCRIPTION', 'READING_ROOM')
-- ;

CREATE TABLE user
(
  id           INTEGER AUTO_INCREMENT NOT NULL
    CONSTRAINT user_pkey
    PRIMARY KEY,
  name         VARCHAR(45)            NOT NULL,
  lastname     VARCHAR(45)            NOT NULL,
  email        VARCHAR(45)            NOT NULL,
  passwordhash VARCHAR(45)            NOT NULL,
  role         VARCHAR(45)            NOT NULL
);

CREATE UNIQUE INDEX user_id_uindex
  ON user (id);

CREATE UNIQUE INDEX user_email_uindex
  ON user (email);

CREATE TABLE user_order
(
  id        INTEGER AUTO_INCREMENT NOT NULL
    CONSTRAINT order_pkey
    PRIMARY KEY,
  editionid INTEGER                NOT NULL,
  userid    INTEGER                NOT NULL
    CONSTRAINT order_user_id_fk
    REFERENCES user
    ON UPDATE CASCADE ON DELETE CASCADE,
  status    VARCHAR(45)            NOT NULL
);

CREATE UNIQUE INDEX order_id_uindex
  ON user_order (id);

CREATE TABLE book_instance
(
  id        INTEGER AUTO_INCREMENT NOT NULL
    CONSTRAINT book_pkey
    PRIMARY KEY,
  editionid INTEGER                NOT NULL
);

CREATE UNIQUE INDEX book_id_uindex
  ON book_instance (id);

CREATE TABLE book_order
(
  id      INTEGER AUTO_INCREMENT NOT NULL
    CONSTRAINT book_order_pkey
    PRIMARY KEY,
  bookid  INTEGER                NOT NULL
    CONSTRAINT book_order_book_id_fk
    REFERENCES book_instance
    ON UPDATE CASCADE ON DELETE CASCADE,
  orderid INTEGER                NOT NULL
    CONSTRAINT book_order_order_id_fk
    REFERENCES user_order
    ON UPDATE CASCADE ON DELETE CASCADE,
  option  VARCHAR(45)            NOT NULL
);

CREATE UNIQUE INDEX book_order_id_uindex
  ON book_order (id);

CREATE TABLE book
(
  id     INTEGER AUTO_INCREMENT NOT NULL
    CONSTRAINT edition_pkey
    PRIMARY KEY,
  author VARCHAR(45),
  title  VARCHAR(45),
  year   INTEGER
);

CREATE UNIQUE INDEX edition_id_uindex
  ON book (id);

ALTER TABLE user_order
  ADD CONSTRAINT order_edition_id_fk
FOREIGN KEY (editionid) REFERENCES book
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE book_instance
  ADD CONSTRAINT book_edition_id_fk
FOREIGN KEY (editionid) REFERENCES book
ON UPDATE CASCADE ON DELETE CASCADE;


DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

CREATE TABLE users
(
  id           SERIAL NOT NULL
    CONSTRAINT user_pkey
    PRIMARY KEY,
  name         VARCHAR(45)            NOT NULL,
  lastname     VARCHAR(45)            NOT NULL,
  email        VARCHAR(45)            NOT NULL,
  passwordhash VARCHAR(45)            NOT NULL,
  role         VARCHAR(45)            NOT NULL
);

CREATE UNIQUE INDEX user_id_uindex
  ON users (id);

CREATE UNIQUE INDEX user_email_uindex
  ON users (email);

CREATE TABLE user_order
(
  id        SERIAL NOT NULL
    CONSTRAINT order_pkey
    PRIMARY KEY,
  bookid INTEGER                NOT NULL,
  userid    INTEGER                NOT NULL
    CONSTRAINT order_user_id_fk
    REFERENCES users
    ON UPDATE CASCADE ON DELETE CASCADE,
  status    VARCHAR(45)            NOT NULL
);

CREATE UNIQUE INDEX order_id_uindex
  ON user_order (id);

CREATE TABLE book_instance
(
  id        SERIAL NOT NULL
    CONSTRAINT book_pkey
    PRIMARY KEY,
  bookid INTEGER                NOT NULL
);

CREATE UNIQUE INDEX book_id_uindex
  ON book_instance (id);

CREATE TABLE book_order
(
  id      SERIAL NOT NULL
    CONSTRAINT book_order_pkey
    PRIMARY KEY,
  book_instanceid  INTEGER                NOT NULL
    CONSTRAINT book_order_book_id_fk
    REFERENCES book_instance
    ON UPDATE CASCADE ON DELETE CASCADE,
  user_orderid INTEGER                NOT NULL
    CONSTRAINT book_order_order_id_fk
    REFERENCES user_order
    ON UPDATE CASCADE ON DELETE CASCADE,
  option  VARCHAR(45)            NOT NULL
);

CREATE UNIQUE INDEX book_order_id_uindex
  ON book_order (id);

CREATE TABLE book
(
  id     SERIAL NOT NULL
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
FOREIGN KEY (bookid) REFERENCES book
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE book_instance
  ADD CONSTRAINT book_edition_id_fk
FOREIGN KEY (bookid) REFERENCES book
ON UPDATE CASCADE ON DELETE CASCADE;


INSERT INTO book (author, title, year) VALUES ('Петр Иванов', 'Азбука', 1954);
INSERT INTO book (author, title, year) VALUES ('Herbert Schildt', 'Java: A Beginner''s Guide, Sixth Edition', 2014);
INSERT INTO book (author, title, year) VALUES ('Bruce Eckel', 'Thinking in Java (4th Edition)', 2016);
INSERT INTO book (author, title, year) VALUES ('Лев Толстой', 'Война и мир', 1978);
INSERT INTO book (author, title, year) VALUES ('Михаил булгаков', 'Собачье сердце', 2001);
INSERT INTO book (author, title, year) VALUES ('Carl Sagan', 'Pale Blue Dot', 2017);
INSERT INTO book (author, title, year) VALUES ('Иван Бунин', 'Темные аллеи', 1998);
INSERT INTO book (author, title, year) VALUES ('Александр Пушкин', 'Евгений Онегин', 2011);
INSERT INTO book (author, title, year) VALUES ('Петр Иванов', 'Азбука для программистов', 1994);
INSERT INTO book (author, title, year) VALUES ('Михаил булгаков', 'Мастер и Маргарита', 2011);

INSERT INTO book_instance (bookid) VALUES (1);
INSERT INTO book_instance (bookid) VALUES (1);
INSERT INTO book_instance (bookid) VALUES (1);
INSERT INTO book_instance (bookid) VALUES (1);
INSERT INTO book_instance (bookid) VALUES (2);
INSERT INTO book_instance (bookid) VALUES (2);
INSERT INTO book_instance (bookid) VALUES (2);
INSERT INTO book_instance (bookid) VALUES (2);
INSERT INTO book_instance (bookid) VALUES (2);
INSERT INTO book_instance (bookid) VALUES (4);
INSERT INTO book_instance (bookid) VALUES (4);
INSERT INTO book_instance (bookid) VALUES (4);
INSERT INTO book_instance (bookid) VALUES (3);
INSERT INTO book_instance (bookid) VALUES (3);
INSERT INTO book_instance (bookid) VALUES (3);
INSERT INTO book_instance (bookid) VALUES (3);
INSERT INTO book_instance (bookid) VALUES (5);
INSERT INTO book_instance (bookid) VALUES (5);
INSERT INTO book_instance (bookid) VALUES (5);
INSERT INTO book_instance (bookid) VALUES (5);
INSERT INTO book_instance (bookid) VALUES (6);
INSERT INTO book_instance (bookid) VALUES (6);
INSERT INTO book_instance (bookid) VALUES (6);
INSERT INTO book_instance (bookid) VALUES (6);
INSERT INTO book_instance (bookid) VALUES (7);
INSERT INTO book_instance (bookid) VALUES (7);
INSERT INTO book_instance (bookid) VALUES (7);
INSERT INTO book_instance (bookid) VALUES (7);
INSERT INTO book_instance (bookid) VALUES (7);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (9);
INSERT INTO book_instance (bookid) VALUES (9);
INSERT INTO book_instance (bookid) VALUES (9);
INSERT INTO book_instance (bookid) VALUES (9);
INSERT INTO book_instance (bookid) VALUES (10);
INSERT INTO book_instance (bookid) VALUES (10);
INSERT INTO book_instance (bookid) VALUES (10);
INSERT INTO book_instance (bookid) VALUES (10);

INSERT INTO users (name, lastname, email, passwordhash, role)
VALUES ('Иван', 'Иванов', 'ivan@ivan.ru', 'fdfsdcdzc', 'USER');
INSERT INTO users (name, lastname, email, passwordhash, role)
VALUES ('Федор', 'Федоров', 'fedor@ivan.ru', 'fdfsdcdrfdsfzc', 'USER');
INSERT INTO users (name, lastname, email, passwordhash, role)
VALUES ('Петр', 'Петров', 'petr@ivan.ru', 'fdfsdcdssdfdzc', 'USER');
INSERT INTO users (name, lastname, email, passwordhash, role)
VALUES ('Семен', 'Семенов', 'semen@ivan.ru', 'fdfsdcdssdfdzc', 'LIBRARIAN');

INSERT INTO user_order (bookid, userid, status) VALUES (1, 1, 'IN_PROGRESS');
INSERT INTO user_order (bookid, userid, status) VALUES (2, 2, 'IN_PROGRESS');
INSERT INTO user_order (bookid, userid, status) VALUES (3, 3, 'IN_PROGRESS');
INSERT INTO user_order (bookid, userid, status) VALUES (4, 4, 'IN_PROGRESS');
INSERT INTO user_order (bookid, userid, status) VALUES (3, 1, 'NEW');

INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (4, 1, 'SUBSCRIPTION');
INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (6, 2, 'SUBSCRIPTION');
INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (11, 3, 'READING_ROOM');
INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (10, 4, 'READING_ROOM');

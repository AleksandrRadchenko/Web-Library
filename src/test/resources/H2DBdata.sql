INSERT INTO book (author, title, year) VALUES ('Петр Иванов', 'Азбука', 1954);
INSERT INTO book (author, title, year) VALUES ('Herbert Schildt', 'Java: A Beginner''s Guide, Sixth Edition', 2014);
INSERT INTO book (author, title, year) VALUES ('Bruce Eckel', '	Thinking in Java (4th Edition)', 2016);
INSERT INTO book (author, title, year) VALUES ('Лев Толстой', 'Война и мир', 1978);

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
INSERT INTO book_instance (bookid) VALUES (3);

INSERT INTO user (name, lastname, email, passwordhash, role)
VALUES ('Иван', 'Иванов', 'ivan@ivan.ru', 'fdfsdcdzc', 'USER');
INSERT INTO user (name, lastname, email, passwordhash, role)
VALUES ('Федор', 'Федоров', 'fedor@ivan.ru', 'fdfsdcdrfdsfzc', 'USER');
INSERT INTO user (name, lastname, email, passwordhash, role)
VALUES ('Петр', 'Петров', 'petr@ivan.ru', 'fdfsdcdssdfdzc', 'USER');
INSERT INTO user (name, lastname, email, passwordhash, role)
VALUES ('Семен', 'Семенов', 'semen@ivan.ru', 'fdfsdcdssdfdzc', 'USER');

INSERT INTO user_order (bookid, userid, status) VALUES (1, 1, 'IN_PROGRESS');
INSERT INTO user_order (bookid, userid, status) VALUES (2, 2, 'NEW');
INSERT INTO user_order (bookid, userid, status) VALUES (3, 3, 'NEW');
INSERT INTO user_order (bookid, userid, status) VALUES (4, 4, 'NEW');

INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (4, 1, 'SUBSCRIPTION');
INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (6, 2, 'SUBSCRIPTION');
INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (11, 3, 'READING_ROOM');
INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (10, 4, 'READING_ROOM');


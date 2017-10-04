create sequence user_id_seq
;

create sequence order_id_seq
;

create sequence book_id_seq
;

create sequence book_order_id_seq
;

create sequence edition_id_seq
;

create type role as enum ('reader', 'librarian')
;

create type status as enum ('NEW', 'IN_PROGRESS', 'CLOSED')
;

create type option as enum ('SUBSCRIPTION', 'READING_ROOM')
;

create table "user"
(
	id serial not null
		constraint user_pkey
			primary key,
	name varchar(45),
	lastname varchar(45) not null,
	email varchar(45) not null,
	passwordhash varchar(45) not null,
	role wl.role not null
)
;

create unique index user_id_uindex
	on "user" (id)
;

create unique index user_email_uindex
	on "user" (email)
;

create table "order"
(
	id serial not null
		constraint order_pkey
			primary key,
	editionid integer not null,
	userid integer not null
		constraint order_user_id_fk
			references "user"
				on update cascade on delete cascade,
	status wl.status not null
)
;

create unique index order_id_uindex
	on "order" (id)
;

create table book_instance
(
	id serial not null
		constraint book_pkey
			primary key,
	editionid integer not null
)
;

create unique index book_id_uindex
	on book_instance (id)
;

create table book_order
(
	id serial not null
		constraint book_order_pkey
			primary key,
	bookid integer not null
		constraint book_order_book_id_fk
			references book_instance
				on update cascade on delete cascade,
	orderid integer not null
		constraint book_order_order_id_fk
			references "order"
				on update cascade on delete cascade,
	option wl.option not null
)
;

create unique index book_order_id_uindex
	on book_order (id)
;

create table book
(
	id serial not null
		constraint edition_pkey
			primary key,
	author varchar(45),
	title varchar(45),
	year timestamp
)
;

create unique index edition_id_uindex
	on book (id)
;

alter table "order"
	add constraint order_edition_id_fk
		foreign key (editionid) references book
			on update cascade on delete cascade
;

alter table book_instance
	add constraint book_edition_id_fk
		foreign key (editionid) references book
			on update cascade on delete cascade
;


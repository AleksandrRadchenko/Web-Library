/*create table user
(
  ID int auto_increment
    primary key,
  email varchar(100) not null,
  passwordHash varchar(40) not null,
  firstname varchar(45) not null,
  lastname varchar(45) not null,
  constraint IDX_USERS_EMAIL
  unique (email)
)
;*/
create database spammer;
create table users
(
    id    serial primary key,
    name  varchar(50),
    email varchar(50)
);

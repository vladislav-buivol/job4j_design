/*
create table body
(
    id   serial primary key,
    name varchar(40)
);
create table engine
(
    id    serial primary key,
    model varchar(40)
);
create table transmission
(
    id    serial primary key,
    model varchar(40)
);
create table car
(
    id           serial primary key,
    name         varchar(50),
    body         int references body (id),
    engine       int references engine (id),
    transmission int references transmission (id)
);
insert into body (name)
values ('sedan');
select *
from car;
insert into body (name)
values ('hatchback');

insert into engine(model)
values ('electrical');
insert into engine(model)
values ('thermal');

insert into transmission(model)
values ('automatic');
insert into transmission(model)
values ('manual');

insert into car(name, body, engine, transmission)
values ('Tesla', 1, 1, 1);
*/
--1
select car.name, b.name, e.model, t.model
from car
         join body b on car.body = b.id
         join engine e on car.engine = e.id
         join transmission t on car.transmission = t.id;

--2

select t.*
from transmission t,
     car c
where t.id != c.transmission;

select b.*
from body b,
     car c
where b.id != c.body;

select e.*
from engine e,
     car c
where e.id != c.engine;
--1
/*create table departments
(
    id   serial primary key,
    name varchar(50)
);
create table employees
(
    id   serial primary key,
    name varchar(50),
    departmentId int references departments(id)
);
insert into departments(name) values ('Marketing');
insert into departments(name) values ('Operations');
insert into departments(name) values ('Operations ');

insert into employees(name, departmentId)values ('Josefa',1);
insert into employees(name, departmentId)values ('Kermit',2);
insert into employees(name, departmentId)values ('Buena',3);
insert into employees(name)values ('Tawanna');
insert into employees(name)values ('Aleshia');
*/

--2
select *
from employees
         left join departments on employees.departmentid = departments.id;

select *
from departments
         left join employees e on departments.id = e.departmentid;

select *
from departments
         cross join employees;

select *
from departments
         full join employees e on departments.id = e.departmentid;

--3
select *
from employees
         left join departments on employees.departmentid = departments.id
where employees.departmentid is null;

--4
select *
from departments
         left join employees e on departments.id = e.departmentid;

select *
from employees
         right join departments d on employees.departmentid = d.id;


--5
/*create table teens
(
    id     serial primary key,
    name   varchar(50),
    gender char
);
insert into teens (name, gender)
values ('Krystle', 'w');

insert into teens (name, gender)
values ('Fredricka', 'w');

insert into teens (name, gender)
values ('Marcellus', 'm');
insert into teens (name, gender)
values ('David', 'm');
*/

select t1.name, t2.name
from teens t1
         cross join teens t2
where t1.gender != t2.gender;
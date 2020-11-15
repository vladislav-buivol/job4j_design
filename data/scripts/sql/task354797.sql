/*create database cars;

create table cars(
    id serial primary key,
    carModel varchar (30) NOT NULL,
    registrationDate DATE NOT NULL
    );
*/
insert into  cars(carmodel, registrationdate)
values ('Škoda',to_date('01.12.2018','DD/MM/YYYY'));
update cars set carmodel = 'Škoda Fabia';
delete from cars;
select * from cars;

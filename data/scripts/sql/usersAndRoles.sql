--3
create database applications;

--4
create table "user"(
id serial primary key,
name varchar(30) NOT NULL
);

create table role(
id serial primary key,
userID int references "user"(id),
role varchar(30)
);

create table rule(
id serial primary key,
RoleRight varchar(30)
);

create table role_rule(
roleID int references role(id),
ruleID int references rule(id)
);

create table comments(
id serial primary key,
userID int references "user"(id),
text varchar (30)
);

create table application(
id serial primary key,
userID int references "user"(id),
text varchar (30)
);

create table attaches(
id serial primary key,
applicationID int references application(id),
text varchar (30)
);

create table category(
id serial primary key,
applicationID int references application(id),
categoryName varchar (30)
);

create table state(
id serial primary key,
applicationID int references application(id),
status varchar (30)
);

--5
insert into "user"(name) values ('Bob');
insert into role(userid, role) VALUES (1,'admin');
insert into rule(roleright) values ('foo');
insert into role_rule(roleid, ruleid) values (1,1);
insert into comments (userid, text) values (1,'Bobs Comment');
insert into application (userid, text) values (1,'Bobs application');
insert into attaches (applicationid, text) VALUES (1,'Attachment');
insert into category (applicationid, categoryname) VALUES (1,'Internal');
insert into state (applicationid, status) VALUES (1, 'pending');

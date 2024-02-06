create table fauna
(
    id serial primary key,
    fname text,
    avg_age int,
    discovery_date date
);
insert into fauna (fname, avg_age, discovery_date) values ('ant', 10000, '185011001');
insert into fauna (fname, avg_age, discovery_date) values ('spider', 2000, '19401112');
insert into fauna (fname, avg_age) values ('goldfish', 11000);
insert into fauna (fname, avg_age, discovery_date) values ('bigfish', 12000, '19710201');
select * from fauna where fname like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '19500101';
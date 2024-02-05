create table car (
id serial primary key,
model varchar(255),
color varchar(255),
power smallint
);
insert into car (model, color, power) values ('bmw', 'black', 500);
update car set color='green';
delete from car;
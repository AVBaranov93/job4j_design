create table car_bodies (
id serial primary key,
name varchar(20)
);
create table car_engines (
id serial primary key,
name varchar(20)
);
create table car_transmissions (
id serial primary key,
name varchar(20)
);
create table cars (
id serial primary key,
name varchar(20),
body_id int references car_bodies (id),
engine_id int references car_engines (id),
transmission_id int references car_transmissions (id)
);

insert into car_bodies (name) values ('sedan');
insert into car_bodies (name) values ('crossover');
insert into car_bodies (name) values ('minivan');
insert into car_engines (name) values ('M270');
insert into car_engines (name) values ('OM615');
insert into car_engines (name) values ('OM906');
insert into car_transmissions (name) values ('9G-Tronic');
insert into car_transmissions (name) values ('NAG3');
insert into car_transmissions (name) values ('7G-Tronic');
insert into cars (name, body_id, engine_id, transmission_id) values ('GLE', 1, 2, 1);
insert into cars (name, body_id, engine_id, transmission_id) values ('GLA-250', 2, 1, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('S63 AMG', 2, 2, 2);
insert into cars (name, body_id, transmission_id) values ('xx', 1, 2);

select c.name марка, b.name кузов, e.name двигатель, t.name трансмиссия from cars c
left join car_bodies b on b.id = c.body_id
left join car_engines e on e.id = c.engine_id
left join car_transmissions t on t.id = c.transmission_id;

select b.name from cars c full join car_bodies b on c.body_id = b.id where c.id is null;
select e.name from cars c full join car_engines e on c.engine_id = e.id where c.id is null;
select t.name from cars c full join car_transmissions t on c.engine_id = t.id where c.id is null;

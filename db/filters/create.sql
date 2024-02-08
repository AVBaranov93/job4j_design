create table type (
	id serial primary key,
	name varchar(50)
);
create table product (
	id serial primary key,
	name varchar(50),
	expired_date timestamp,
	price float,
	type_id int references type (id)
);
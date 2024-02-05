create table country (
country_name varchar(255) primary key,
area int
);
create table capital (
capital_name varchar(255) primary key,
area int,
country_name varchar(255) references country (country_name) unique
);
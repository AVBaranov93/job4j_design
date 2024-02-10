create table departments (
id serial primary key,
name varchar(30)
);
create table employees (
id serial primary key,
name varchar(30),
department_id int references departments (id)
);

insert into departments (name) values ('east');
insert into departments (name) values ('west');
insert into departments (name) values ('south');
insert into departments (name) values ('north');
insert into employees (name, department_id) values ('jack', 1);
insert into employees (name, department_id) values ('kate', 1);
insert into employees (name, department_id) values ('john', 2);
insert into employees (name, department_id) values ('olivia', 1);
insert into employees (name, department_id) values ('pedro', 3);
insert into employees (name, department_id) values ('vanessa', 2);

select * from departments d left join employees e on e.department_id = d.id;
select * from departments d right join employees e on e.department_id = d.id;
select * from departments d full join employees e on e.department_id = d.id;
select * from departments cross join employees;

select d.name from departments d left join employees e on e.department_id = d.id where e.id is null;

select e.id, e.name, e.department_id, d.id, d.name from employees e right join departments d on e.department_id = d.id;
select e.id, e.name, e.department_id, d.id, d.name from departments d left join employees e on e.department_id = d.id;
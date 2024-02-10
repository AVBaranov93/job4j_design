create table teens (
name varchar(30),
gender varchar(1)
);

insert into teens values ('kate', 'f');
insert into teens values ('jack', 'm');
insert into teens values ('olivia', 'f');
insert into teens values ('john', 'm');

select male.name, female.name from (select * from teens where gender like 'm') as male cross join
(select * from teens where gender like 'f') as female;
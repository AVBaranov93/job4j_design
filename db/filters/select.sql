select p.* from product p join type t on t.id = p.type_id where t.name like '%сыр%';
select p.* from product p join type t on t.id = p.type_id where p.name like '%мороженое%';
select p.* from product p join type t on t.id = p.type_id where p.expired_date < current_date;
select * from product where price = (select max(price) from product);
select t.name имя_типа, count(p.name) количество from product p join type t on t.id = p.type_id group by t.name;
select p.* from product p join type t on t.id = p.type_id where t.name like any ('{%сыр%, %молоко%}');
select t.name тип_продукта, count(p.name) количество from product p join type t on t.id = p.type_id group by t.name having count(p.name) < 10;
select p.name название_продукта, p.expired_date срок_годности, p.price цена, t.name тип_продукта from product p join type t on t.id = p.type_id;
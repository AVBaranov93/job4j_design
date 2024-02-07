create table shops (
id serial primary key,
name varchar(30)
);
create table products (
id serial primary key,
name varchar(50),
price int
);
create table shops_products (
id serial primary key,
shop_id int references shops (id),
product_id int references products (id)
);

insert into shops (name) values ('dns');
insert into shops (name) values ('m-vidio');
insert into products (name, price) values ('iphone 15 pro max', 300000);
insert into products (name, price) values ('galaxy s24 ultra', 200000);
insert into products (name, price) values ('huawei mate x3', 150000);
insert into shops_products (shop_id, product_id) values (1, 1);
insert into shops_products (shop_id, product_id) values (1, 2);
insert into shops_products (shop_id, product_id) values (2, 2);
insert into shops_products (shop_id, product_id) values (1, 3);

select s.name магазин, p.name товар, p.price цена from shops s join shops_products sp on sp.shop_id = s.id join products p on p.id = sp.product_id;
select s.name as магазин, p.name as товар, p.price as цена from shops as s join shops_products as sp on sp.shop_id = s.id
join products as p on p.id = sp.product_id where p.price >= 200000 and p.name like '%galaxy%';
select s.name as магазин, p.name as товар, p.price as цена from shops as s join shops_products as sp on sp.shop_id = s.id
join products as p on p.id = sp.product_id where s.name like '%dn%' and p.name like '%galaxy%';
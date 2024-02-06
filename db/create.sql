create table roles (
id serial primary key,
role varchar(255)
);
create table rules (
id serial primary key,
rule varchar(255)
);
create table roles_rules (
id serial primary key,
role_id int references roles (id),
rule_id int references rules (id)
);
create table users (
id serial primary key,
username varchar(255),
role_id int references roles (id)
);
create table categories (
id serial primary key,
category varchar(255)
);
create table states (
id serial primary key,
state varchar(255)
);
create table items (
id serial primary key,
item varchar(255),
user_id int references users (id),
category_id int references categories (id),
state_id int references states (id)
);
create table comments (
id serial primary key,
comment varchar(255),
item_id int references items (id)
);
create table attachs (
id serial primary key,
attached_file varchar(255),
item_id int references items (id)
);
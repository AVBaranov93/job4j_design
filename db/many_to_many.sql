create table authors (
id serial primary key,
author_name varchar(255)
);
create table books (
id serial primary key,
book_name varchar(255)
);
create table authors_books (
id serial primary key,
book_id int references books (id),
author_id int references authors (id)
);
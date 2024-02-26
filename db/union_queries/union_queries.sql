select name from movie intersect
select title from book;

select title from book except
select name from movie;

(select name from movie except
select title from book)
union all
(select title from book except
select name from movie);
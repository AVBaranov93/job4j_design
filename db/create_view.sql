select a.name as author, count(a.name) as amount from authors a
join books b on b.author_id = a.id
join orders o on o.book_id = b.id
group by a.name
order by count(a.name) desc
limit 1
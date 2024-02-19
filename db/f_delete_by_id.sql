create
or replace function f_delete_by_id(u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        delete from products
		where id = u_id;
		select into result u_id;
		return result;
    end;
$$;
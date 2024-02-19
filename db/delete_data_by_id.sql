create
or replace procedure delete_data_by_id(u_id integer)
language 'plpgsql'
as $$
    BEGIN
		delete from products
		where id = u_id;
    END;
$$;
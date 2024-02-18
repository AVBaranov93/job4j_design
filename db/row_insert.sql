create
or replace function row_insert()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
		values
		(new.name, new.price, CURRENT_TIMESTAMP);
		return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger row_insert_trigger
    after insert
    on products
    for each row
    execute procedure row_insert();
create
or replace function row_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger row_tax_trigger
    before insert
    on products
    for each row
    execute procedure row_tax();
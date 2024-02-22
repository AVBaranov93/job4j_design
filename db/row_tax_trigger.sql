create
or replace function row_tax()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger row_tax_trigger
    before insert
    on products
    for each row
    execute procedure row_tax();
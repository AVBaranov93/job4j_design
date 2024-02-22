begin;
declare curs_prod scroll cursor for select * from products;
move forward 20 from curs_prod;
move backward 5 from curs_prod;
move backward 8 from curs_prod;
move backward 5 from curs_prod;
move backward 1 from curs_prod;
close curs_prod;
commit;
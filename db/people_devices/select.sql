select avg(d.price) average_price from people p join devices_people dp on dp.people_id = p.id join devices d on d.id = dp.device_id;
select p.name human, avg(d.price) average_price from people p join devices_people dp on dp.people_id = p.id
join devices d on d.id = dp.device_id group by human having avg(d.price) > 5000;
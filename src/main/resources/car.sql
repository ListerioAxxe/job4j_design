select * from car c
join kuzov k on c.kuzov_id = k.id
join engine e on c.engine_id = e.id
join transmission t on c.transmission_id = t.id;

select k.name from kuzov k left join car c on c.kuzov_id = k.id where c.kuzov_id is null;
select e.name from engine e left join car c on c.engine_id = e.id where c.engine_id is null;
select t.name from transmission t left join car c on c.transmission_id = t.id where c.transmission_id is null;

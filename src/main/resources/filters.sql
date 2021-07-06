select * from product
join type on product.type_id = type.id
where type.name = 'СЫР';

select * from product where name = 'Рогачев';

select * from product where expired_date < current_date;

select * from product order by price desc limit 1;

select type.name, count(product) as amount
from type
join product on type.id = product.type_id
group by type.name
having count(product) < 10;

select * from product
join type on product.type_id = type.id
where type.name = 'СЫР'
or type.name = 'МОЛОКО';

select type.name, count(product) as Количество
from product
join type on product.type_id = type.id
group by type.name
having count(product) < 10;

select * from product
join type on product.type_id = type.id;


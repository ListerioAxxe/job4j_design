select * from employes e left join departments d on e.id_department = d.id;

select * from employes e right join departments d on e.id_department = d.id;

select * from employes e full join departments d on e.id_department = d.id;

select * from employes e cross join departments d;

select * from departments d left join employes e on e.id_department = d.id where e.id is null;


select *
from employes e
right join departments d on d.id = e.id_department;

select *
from departments d
left join employes e on d.id = e.id_department;

select t1.name Boys, t2.name Girls from teens t1 cross join teens t2 where  t1.gender != t2.gender and t1.gender like 'm%';


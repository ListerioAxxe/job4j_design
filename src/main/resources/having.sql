create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Mobile', 55.7);
insert into devices(name, price) values ('Ipad', 67.7);
insert into devices(name, price) values ('Earpods', 31.5);
insert into devices(name, price) values ('Computer', 81.9);
insert into devices(name, price) values ('Notepad', 99.4);

insert into people(name) values ('Kolya');
insert into people(name) values ('Ivan');
insert into people(name) values ('Andru');
insert into people(name) values ('Igor');
insert into people(name) values ('Masha');
insert into people(name) values ('Kirill');
insert into people(name) values ('Evgen');

insert into devices_people(device_id, people_id) values (2,1);
insert into devices_people(device_id, people_id) values (1,1);
insert into devices_people(device_id, people_id) values (3,5);
insert into devices_people(device_id, people_id) values (4,1);


select avg(price) from devices;

select p.name, avg(price)
from people p
join devices_people dp on dp.people_id = p.id
join devices d on d.id = dp.device_id
group by p.name

select p.name, avg(price)
from people p
join devices_people dp on dp.people_id = p.id
join devices d on d.id = dp.device_id
group by p.name
having avg(price) > 50
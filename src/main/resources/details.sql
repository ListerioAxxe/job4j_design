create table kuzov(
	id serial primary key,
	name varchar(10)
)

create table engine(
	id serial primary key,
	name varchar(10)
)

create table transmission(
	id serial primary key,
	name varchar(10)
)

create table car(
	id serial primary key,
	kuzov_id int references kuzov(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
)

insert into kuzov(name) values('Sedan');
insert into kuzov(name) values('Pickup');
insert into kuzov(name) values('Universal');
insert into kuzov(name) values('Kamaz');

insert into engine(name) values('V4');
insert into engine(name) values('V6');
insert into engine(name) values('V8');
insert into engine(name) values('V10');

insert into transmission(name) values('mechanic');
insert into transmission(name) values('automatic');
insert into transmission(name) values('robot');
insert into transmission(name) values('variator');

insert into car(kuzov_id, engine_id, transmission_id) values(2, 1, 1);
insert into car(kuzov_id, engine_id, transmission_id) values(1, 3, 3);
insert into car(kuzov_id, engine_id, transmission_id) values(3, 3, 2);
insert into car(kuzov_id, engine_id, transmission_id) values(2, 2, 1);


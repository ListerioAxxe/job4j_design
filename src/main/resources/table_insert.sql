create table departments(
	id serial primary key,
	name varchar(10)
)

create table employes(
	id serial primary key,
	name varchar(10),
	id_department int references departments(id)
)

insert into departments(name) values('Programer');
insert into departments(name) values('Builder');
insert into departments(name) values('Barmen');

insert into employes(name, id_department) values('Ivan', 2);
insert into employes(name, id_department) values('Kolya', 1);

create table teens(
	id serial primary key,
	name varchar(15),
	gender varchar(1)
)

insert into teens(name, gender) values  ('Vasya', 'm');
insert into teens(name, gender) values  ('Fedya', 'm');
insert into teens(name, gender) values  ('Vika', 'f');
insert into teens(name, gender) values  ('Ivan', 'm');
insert into teens(name, gender) values  ('Sveta', 'f');
insert into teens(name, gender) values  ('Yana', 'f');
create table my_streetFood(
	id serial primary key,
	name varchar (255),
	position text,
	area integer
);
insert into my_streetFood (name, position, area) values ('Kofeina', 'Beloryssk', '77');
update my_streetFood set position = 'street';
delete from my_streetFood;
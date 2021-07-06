create table product(
	id serial primary key,
	name varchar(15),
	type_id int references type(id),
	expired_date date,
	price float
)
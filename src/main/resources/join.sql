create table worker(
  id serial primary key,
  name varchar(15),
  office int references offices(id)
)

create table offices(
  id serial primary key,
  office_name varchar(15)
)

insert into worker(name, office) values ('Kolya', 1);
insert into worker(name, office) values ('Ivan', 3);
insert into worker(name, office) values ('Andru', 4);

insert into offices(office_name) values ('Moscow City');
insert into offices(office_name) values ('Federation');
insert into offices(office_name) values ('Avtozavodskiy Street');
insert into offices(office_name) values ('Lublino');

select * from worker join offices on worker.office = offices.office_name;
select * from worker w join offices o on w.office = o.office_name;
select w.name as Имя, o.office_name as Офис from worker w join offices o on w.office = o.office_name;





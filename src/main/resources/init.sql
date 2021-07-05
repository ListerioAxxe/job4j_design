create table users(
 id serial primary key,
 name varchar(255),
 role_id int references role(id)
);

create table role(
 id serial primary key,
 name varchar(255)
);

create table rules(
 id serial primary key,
 name varchar(255)
);

create table item(
 id serial primary key,
 name varchar(255),
 user_id int references users(id),
 category_id references category(id),
 state_id references state(id)
);

create table comments(
 id serial primary key,
 name varchar(255),
 comments_user int users(id)
);

create table attachs(
 id serial primary key,
 name varchar(255),
 item_id int references item(id)
);

create table state(
 id serial primary key,
 name varchar(255)
);

create table category(
 id serial primary key,
 name varchar(255)
);

create table rules_role(
 id serial primary key,
 name varchar(255),
 role_id int references role(id),
 rule_id int references rules(id)
);

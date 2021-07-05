insert into users(name, role_id) values ('Ivan', 1);
insert into role(name) values ('Admin');
insert into rules(name) values ('Main');
insert into item(name, user_id, category_id, state_id) values ('Input first', 1, 1, 1);
insert into comments(name, coments_user) values ('Danger', 1);
insert into attachs(name, item_id) values ('Road map', 1);
insert into state(name) values ('Done');
insert into category(name) values ('Representative');
insert into rules_role(name, role_id, rule_id) values ('Collective task', 1, 1);
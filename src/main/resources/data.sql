insert into role(id, name) values (1, 'ADMIN');
insert into role(id, name) values (2, 'USER');

insert into store_user(id, password, username) values (1, '{noop}admin', 'admin');
insert into store_user(id, password, username) values (2, '{noop}user', 'user');

insert into user_role(user_id, role_id) values (1,1);
insert into user_role(user_id, role_id) values (2,2);
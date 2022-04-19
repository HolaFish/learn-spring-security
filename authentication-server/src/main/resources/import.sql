insert into users (id, username, password, enabled)
values (1, 'Tom', '123456', 1);

insert into authorities (id, authority)
values (1, 'Admin');

insert into users_authorities(user_id, auth_id)
values (1, 1);
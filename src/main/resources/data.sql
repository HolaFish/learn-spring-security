insert into users (id, username, password, enabled)
values (1, 'Tom', "123456", 1);

insert into authorities (id, authority)
values (1, "ADMIN");

insert into users_authorities(account_id, authorities_id)
values (1, 1);
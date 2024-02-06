insert into roles (role) values ('admin');
insert into rules (rule) values ('all');
insert into roles_rules (role_id, rule_id) values (1, 1);
insert into users (username, role_id) values ('postgres', 1);
insert into items (item, user_id) values ('add item', 1);
insert into comments (comment, item_id) values ('test comment', 1);
insert into attaches (attached_file, item_id) values ('test file', 1);
insert into categories (category, item_id) values ('test category', 1);
insert into states (state, item_id) values ('test state', 1);
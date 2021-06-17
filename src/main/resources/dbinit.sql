INSERT INTO user (id, age, name, password, weight)
VALUES  (1,24,'Max',100,66);

INSERT INTO role (id,name)
VALUES (1,'ROLE_ADMIN');

INSERT INTO user_role (user_id,role_id)
VALUES((select id from user where name = 'Max'),(select id from role where name = 'ROLE_ADMIN'));


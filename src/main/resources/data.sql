INSERT INTO roles (description, name) VALUES ('Admin role', 'ADMIN');
INSERT INTO roles (description, name) VALUES ('Manager role', 'MANAGER');
INSERT INTO roles (description, name) VALUES ('User role', 'USER');

INSERT INTO users (email, password, username) VALUES ('admin@gmail.com','$2a$10$e/Fvvf1jr8IK8HTH087pyOh0ZHWdv0wd1WnrOAKQnzy1X0yHu1cSq', 'admin');
INSERT INTO users_role (user_id, role_id) VALUES (1,1);
INSERT INTO users_role (user_id, role_id) VALUES (1,2);

INSERT INTO products (name, price) VALUES ('iphone 13 pro max', 1250.90);
INSERT INTO products (name, price) VALUES ('macbook air 13', 1850.90);
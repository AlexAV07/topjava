DELETE FROM user_roles;
DELETE FROM users;
delete from meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals(dateTime,description,calories,user_id)
VALUES ('2017-04-01 09:00','Завтрак', 700,100000);
INSERT INTO meals(dateTime,description,calories,user_id)
VALUES ('2017-04-01 13:20','Обед', 1700,100000);
INSERT INTO meals(dateTime,description,calories,user_id)
VALUES ('2017-04-01 19:00','Ужин', 400,100000);
INSERT INTO meals(dateTime,description,calories,user_id)
VALUES ('2017-04-02 10:00','Завтрак', 1000,100001);
INSERT INTO meals(dateTime,description,calories,user_id)
VALUES ('2017-04-02 14:00','Обед', 800,100001);
INSERT INTO meals(dateTime,description,calories,user_id)
VALUES ('2017-04-01 16:00','Полдник', 100,100000);
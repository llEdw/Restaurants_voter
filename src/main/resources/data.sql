DELETE FROM restaurants;
-- DELETE FROM dishes;
DELETE FROM users;

INSERT INTO USERS (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('user@gmail.com', 'User_First', 'User_Last', '{noop}password'),
       ('admin@javaops.ru', 'Admin_First', 'Admin_Last', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO restaurants (name, address) VALUES
('Toscana Grill', 'наб. канала Грибоедова, 64'),
('Katyusha', 'Невский пр., 22/24'),
('Italy', 'Большая Морская ул., 14'),
('Terrassa', 'Казанская ул., 3А'),
('Teplo', 'Большая Морская ул., 45'),
('Пхали-Хинкали', 'Большая Морская ул., 27');
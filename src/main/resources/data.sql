DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM users;
DELETE FROM votes;

INSERT INTO USERS (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('user@gmail.com', 'User_First', 'User_Last', '{noop}password'),
       ('admin@javaops.ru', 'Admin_First', 'Admin_Last', '{noop}admin'),
       ('enokent1990@yandex.ru', 'Игорь', 'sa_Last', '{noop}password'),
       ('OlegEvgenievich@gmail.com', 'Олег', 'csaas', '{noop}password'),
       ('lv1951@rambler.ru', 'ЛюдвигВалентинович', 'wqcsa', '{noop}password'),
       ('zaharovii@mail.ru', 'Ильдар', 'csa', '{noop}password'),
       ('vnkruchkov@rambler.ru', 'Володя', 'sact', '{noop}password');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4),
       ('USER', 5),
       ('USER', 6),
       ('USER', 7);

INSERT INTO restaurants (name, address)
VALUES ('Toscana Grill', 'наб. канала Грибоедова, 64'),
       ('Katyusha', 'Невский пр., 22/24'),
       ('Italy', 'Большая Морская ул., 14'),
       ('Terrassa', 'Казанская ул., 3А'),
       ('Teplo', 'Большая Морская ул., 45'),
       ('Пхали-Хинкали', 'Большая Морская ул., 27');

INSERT INTO dishes (restaurant_id, name, price)
VALUES (1, 'Тартар из говядины', 590),
       (1, 'Тальятта с соусом «трюфель»', 690),
       (2, 'Корейка оленя с брусничным соусом', 1280),
       (2, 'Белокорый палтус с горчичным пюре', 980),
       (3, 'Медальоны из телятины с рукколой под соусом из лесных грибов', 890),
       (3, 'Пицца «Боскайола» с лесными грибами и панчеттой', 570),
       (4, 'Креветки, жаренные на сковороде с чесноком', 1090),
       (4, 'Осьминог с овощами', 2150),
       (5, 'Судак al burro с черри и картофельным пюре', 580),
       (5, 'Овощное карри с рисом и куриным бедром', 1200),
       (6, 'Гамарджоба', 290),
       (6, 'Оджахури', 390);
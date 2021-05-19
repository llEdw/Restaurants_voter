DROP TABLE IF EXISTS restaurants;
-- DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS user_role;

CREATE TABLE restaurants
(
    id INT IDENTITY PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    address VARCHAR(128) NOT NULL,
    constraint name_address UNIQUE (name, address)
);

-- CREATE TABLE dishes
-- (
--     id INT IDENTITY PRIMARY KEY,
--     restaurant_id INT NOT NULL,
--     name VARCHAR(100) NOT NULL,
--     price INT NOT NULL,
--     FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
-- );

CREATE TABLE users
(
    id INT IDENTITY PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    role VARCHAR
);

CREATE TABLE user_role
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_unique UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);
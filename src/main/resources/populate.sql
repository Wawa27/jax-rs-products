-- auto-generated definition
create table product
(
    id          int auto_increment
        primary key,
    quantity    int default 0  not null,
    name        varchar(32)    not null,
    description varchar(128)   not null,
    price       decimal(10, 2) not null,
    detail      varchar(64)    null,
    info        varchar(64)    null,
    image       varchar(64)    null
);

INSERT INTO products.product (id, quantity, name, price, detail, info, image)
VALUES (1, 5, 'Rubik\'s cube', 10, 'Rubik\'s cube ...', 'Rubik\'s cube ...', 'Image rubik\'s cube');

INSERT INTO products.product (id, quantity, name, price, detail, info, image)
VALUES (1, 5, 'Ballon de foot', 7, 'Ballon de foot ...', 'Ballon de foot ...', 'Image Ballon de foot');
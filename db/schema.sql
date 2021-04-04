CREATE TABLE types(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE rules(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE accidents (
    id serial primary key,
    name varchar(2000),
    text varchar(2000),
    address varchar(255),
    type_id int references types(id)
);

CREATE TABLE accident_rules(
    id serial primary key,
    rule_id int references rules(id),
    accident_id int references accidents(id)
);

INSERT INTO types(name) VALUES ('Две машины'), ('Машина и человек'), ('Машина и велосипед');
INSERT INTO rules(name) VALUES ('Статья 1'), ('Статья 2'), ('Статья 3');
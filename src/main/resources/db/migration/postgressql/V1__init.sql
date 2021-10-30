CREATE TABLE IF NOT EXISTS users
(
    id serial,
    name varchar(50),
	password varchar,    
	email varchar(100),
    primary key(id)
);



CREATE TABLE IF NOT EXISTS wallet(
id serial,
name varchar(60),
value numeric(10,2),
primary key (id));

CREATE TABLE IF NOT EXISTS users_wallet(
id serial,
wallet integer,
users integer,
primary key(id),
foreign key(users) references users(id),
foreign key(wallet) references wallet(id));

CREATE TABLE IF NOT EXISTS wallet_items(
id serial,
wallet integer,
date date,
type varchar(2),
description varchar(500),
value numeric(10,2),
primary key(id),
foreign key(wallet) references wallet(id));
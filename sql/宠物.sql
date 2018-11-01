drop database if exists petDB;
create database petDB;
use petDB;
CREATE TABLE `user`
(
u_id int PRIMARY KEY auto_increment,
user_name VARCHAR(20),
first_name VARCHAR(20),
last_name VARCHAR(20),
email VARCHAR(20),
`password` VARCHAR(20),
phone VARCHAR(20),
user_status int
);


CREATE TABLE category
(
c_id int PRIMARY KEY auto_increment,
c_name VARCHAR(20)
);
CREATE TABLE tag
(
t_id int PRIMARY KEY auto_increment,
t_name VARCHAR(20)
);
CREATE TABLE pet
(
p_id int primary key auto_increment,
c_id int,
p_name VARCHAR(20),
photo_urls VARCHAR(20),
t_id int,
`status` VARCHAR(10)
);

CREATE TABLE `order`
(
o_id int,
p_id int,
quantity int,
ship_date datetime,
ostatus enum('placed','approved','delivered'),
complete bit(1)  default 0
);
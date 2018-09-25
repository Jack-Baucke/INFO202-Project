/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  bauja773
 * Created: 13/08/2018
 */

create table Product (
Product_ID varchar(50),
Product_Name varchar(50) not null,
Product_Description varchar(200),
Product_Category varchar(30) not null,
Product_Price decimal(10, 2) not null,
Product_Quantity int not null,
constraint Product_PK primary key (Product_ID)
);

create table customer (
person_id varchar(50) auto_increment,
username varchar(50) unique not null,
first_name varchar(50) not null,
surname varchar(50) not null,
password varchar(50) not null,
credit_card varchar(20) not null,
email varchar(50) not null,
shipping_address varchar(50) not null,
constraint customer_pk primary key (person_id)
);

create table sale (
sale_id int auto_increment,
date timestamp,
status varchar(50),
person_id int not null,
constraint sale_id_pk primary key (sale_id),
constraint sale_fk foreign key (person_id) references customer
);

create table sale_item (
quantity int,
sale_price decimal(10, 2),
product_id varchar(50),
sale_id varchar(50),

constraint sale_item_pk primary key (product_id, sale_id),
constraint sale_item_fk_product foreign key (product_id) references product,
constraint sale_item_fk_sale foreign key (sale_id) references sale
);
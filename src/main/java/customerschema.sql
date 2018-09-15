/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  bauja773
 * Created: 14/09/2018
 */

create table customer (
person_id varchar(50),
username varchar(50) unique not null,
first_name varchar(50) not null,
surname varchar(50) not null,
password varchar(50) not null,
credit_card varchar(20) not null,
email varchar(50) not null,
shipping_address varchar(50) not null,
constraint customer_pk primary key (person_id)
);

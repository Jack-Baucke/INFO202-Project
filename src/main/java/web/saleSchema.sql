/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  bauja773
 * Created: 24/09/2018
 */

create table sale (

sale_id varchar(20) auto_increment,
date timestamp not null,
status varchar(50),
username varchar(50),

constraint sale_id_pk primary key (sale_id),
constraint username_fk foreign key (username) references customer

);

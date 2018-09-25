/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  bauja773
 * Created: 24/09/2018
 */

create table sale_item (

quantity int,
price decimal(10, 2),

product_id varchar(50),
sale_id varchar(20),

constraint sale_item_pk primary key (product_id, sale_id),

constraint product_id_fk foreign key (product_id) references product,
constraint sale_id_fk foreign key (sale_id) references sale

);

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
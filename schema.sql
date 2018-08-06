/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  bauja773
 * Created: 6/08/2018
 */


create table Product (
Product_ID varchar(50),
Product_Name varchar(50),
Product_Description varchar(200),
Product_Category varchar(30),
Product_Price decimal(10, 2),
Product_Quantity int,
constraint Product_PK primary key (Product_ID)
);



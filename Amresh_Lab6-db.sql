Drop database studentdb;
create database studentdb;
use studentdb;

insert into student(id,country,course,first_name,last_name)
 values(1,'India','B.Tech','Noopur','Mondal'),
	   (2,'Canada','B.Arch','Gurpreet','Singh'),
       (3,'Singapore','B.A.','Senthil','Srinivasan'),
       (4,'USA','B.Sc','Nic','Byrd');
insert into roles(role_id,name)
values(1,'ADMIN'),
	  (2,'USER'); 
insert into users(user_id,password,username)
values(1,'$2a$12$KTaxK.i7cV4raP25V6uGFupv4YqneVTux/IflDgyuYLU/7PyGeGOu','admin'),
	  (2,'$2a$12$9TrO3woZSh40BvvwslmQq.1MWa7KtN0oWNX4ADgkABASrcqpak5.6','user');
      
insert into users_roles(user_id,role_id)
values(1,1),
	  (2,2);
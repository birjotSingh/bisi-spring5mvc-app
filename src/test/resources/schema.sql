drop table expense1 if exists;

create table expense1 (id int auto_increment, name varchar(20),transaction_type varchar(6), amount int, cdate varchar(50), dateEdited varchar(50), primary key (id));
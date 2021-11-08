CREATE TABLE usuarios(
     id bigint not null auto_increment,
     nome varchar(100)not null,
     login varchar(100) not null,
     senha varchar(300) not null,
     primary key(id));
create table clientes(

    id bigint not null auto_increment,
    nome varchar(255) not null,
    email varchar(255) not null,
    telefone varchar(255) not null,
    cpf varchar(255) not null,
    saldo varchar(255) not null,

    primary key(id)

);
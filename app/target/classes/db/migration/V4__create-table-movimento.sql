create table movimentos(

    id bigint not null auto_increment,
    cliente_id bigint not null,
    valor varchar(255) not null,

    primary key(id),
    constraint fk_movimento_cliente_id foreign key(cliente_id) references clientes(id)
);
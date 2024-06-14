create schema rl_api_products;
set
search_path
to rl_api_products;


create table rl_product_type
(
    id_rl_product_type serial
        primary key,
    description        varchar not null,
    deleted_at         timestamp
);

create table rl_product
(
    id_rl_product      serial
        primary key,
    name               varchar not null,
    description        varchar not null,
    large_description  text not null,
    deleted_at         timestamp,
    id_rl_product_type integer not null
        constraint fk_product_type
            references rl_product_type (id_rl_product_type)
);

create table rl_product_image
(
    id_rl_product_image serial
        primary key,
    id_rl_product       integer not null
        references rl_product (id_rl_product),
    provider            varchar not null,
    provider_link       varchar not null,
    deleted_at          timestamp
);

create table rl_favorites (
    id_favorites serial primary key,
    id_rl_product int not null,
    foreign key (id_rl_product) references rl_product(id_rl_product),
);


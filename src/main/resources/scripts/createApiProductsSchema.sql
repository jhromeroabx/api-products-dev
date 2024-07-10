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
    large_descripcion  text not null,
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

ALTER TABLE rl_product  ADD COLUMN daily_offer BOOLEAN DEFAULT FALSE;
CREATE UNIQUE INDEX unique_daily_offer ON rl_products(daily_offer) WHERE daily_offer IS TRUE;



CREATE TABLE last_user_product (
    id_last_user_product SERIAL PRIMARY KEY,
    id_user INT not NULL,
    id_rl_product INT NOT NULL,
    deleted_at TIMESTAMP NULL,
    FOREIGN KEY (id_rl_product) REFERENCES rl_product(id_rl_product)
);
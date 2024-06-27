SET search_path TO rl_api_products;

CREATE TABLE user_favorite_product (
    id_favorite SERIAL PRIMARY KEY,
    id_user INTEGER NOT NULL,
    id_product INTEGER NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (id_product) REFERENCES rl_product(id_rl_product)
);

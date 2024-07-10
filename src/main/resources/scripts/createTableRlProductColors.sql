SET search_path TO rl_api_products;

CREATE TABLE rl_product_colors (
    id_product_color SERIAL PRIMARY KEY,
    id_rl_product INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_rl_product) REFERENCES rl_product(id_rl_product)
);
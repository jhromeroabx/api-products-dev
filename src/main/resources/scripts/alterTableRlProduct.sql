ALTER TABLE rl_api_products.rl_product
    ADD COLUMN currency VARCHAR(10) NOT NULL;

ALTER TABLE rl_api_products.rl_product
    ADD COLUMN price DECIMAL(10, 2) NOT NULL;

ALTER TABLE rl_api_products.rl_product
    RENAME COLUMN large_descripcion TO large_description;

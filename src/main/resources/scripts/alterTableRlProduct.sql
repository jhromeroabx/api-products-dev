ALTER TABLE rl_product
ADD COLUMN currency varchar(10) NOT NULL,
ADD COLUMN price decimal(10, 2) NOT NULL;

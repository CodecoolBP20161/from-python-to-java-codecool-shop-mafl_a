DROP TABLE IF EXISTS products, productCategoies, suppliers;

CREATE TABLE products
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(40),
  description TEXT,
  default_price REAL,
  currency MONEY,
  product_category VARCHAR(40),
  supplier VARCHAR(40)

);
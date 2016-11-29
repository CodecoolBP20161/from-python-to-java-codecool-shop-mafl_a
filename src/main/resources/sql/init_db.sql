DROP TABLE IF EXISTS products, productCategoies, suppliers;

CREATE TABLE products
(
  id varchar(36) PRIMARY KEY,
  name varchar(40),
  description text,
  default_price real,
  currency money,
  product_category varchar(40),
  supplier varchar(40)

);
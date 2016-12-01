DROP TABLE IF EXISTS products, product_categories, suppliers;

CREATE TABLE product_categories (
  id SERIAL PRIMARY KEY,
  name VARCHAR(40),
  description TEXT,
  department VARCHAR(40)
);

CREATE TABLE suppliers(
  id SERIAL PRIMARY KEY,
  name VARCHAR(40),
  description TEXT
);

CREATE TABLE products(
  id SERIAL PRIMARY KEY,
  name VARCHAR(40),
  description TEXT,
  default_price REAL,
  currency TEXT,
  product_category INTEGER REFERENCES product_categories,
  supplier INTEGER REFERENCES suppliers
);

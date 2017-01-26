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

CREATE TABLE customer(
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(40),
  last_name VARCHAR(40),
  email VARCHAR(40),
  phone_number VARCHAR(15),
  address VARCHAR(40),
  city VARCHAR(20),
  country VARCHAR(20),
  zipcode VARCHAR(10)
);

CREATE TABLE "order"(
  id SERIAL PRIMARY KEY,
  customer INTEGER REFERENCES customer
);

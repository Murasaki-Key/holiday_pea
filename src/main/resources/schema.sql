--CREATE DATABASE spring_holiday;

DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS eat;
DROP TABLE IF EXISTS act;

CREATE TABLE account(
  id SERIAL PRIMARY KEY,
  name TEXT,
  password TEXT
);

CREATE TABLE eat(
  id SERIAL PRIMARY KEY,
  categoryid INTEGER,
  subcategoryid INTEGER,
  seasonid INTEGER,
  name TEXT
);

CREATE TABLE act(
  id SERIAL PRIMARY KEY,
  categoryid INTEGER,
  vehicleid INTEGER,
  weatherid INTEGER,
  seasonid INTEGER,
  name TEXT
);
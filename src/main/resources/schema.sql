CREATE DATABASE spring_holiday;

DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS eat;
DROP TABLE IF EXISTS do;

CREATE TABLE account(
  id SERIAL PRIMARY KEY,
  name TEXT
  password TEXT
);

CREATE TABLE eat(
  id SERIAL PRIMARY KEY,
  categoryid INTEGER,
  seasonid INTEGER,
  name TEXT
);


CREATE TABLE do(
  id SERIAL PRIMARY KEY,
  categoryid INTEGER,
  seasonid INTEGER,
  name TEXT
);
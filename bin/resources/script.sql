CREATE TABLE IF NOT EXISTS Section (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE
);

CREATE TABLE IF NOT EXISTS Status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(25) UNIQUE
);

CREATE TABLE IF NOT EXISTS Local (
    id SERIAL PRIMARY KEY,
    num VARCHAR(5),
    location VARCHAR(10),
    "type" VARCHAR(3)
);

CREATE TABLE IF NOT EXISTS Cours (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE,
    section_id INTEGER
    REFERENCES Section(id) ON DELETE SET NULL,
    CONSTRAINT unique_cours_per_section UNIQUE (section_id, name)
);
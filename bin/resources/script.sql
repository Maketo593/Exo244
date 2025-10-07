CREATE TABLE IF NOT EXISTS Section (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(30) UNIQUE
);

CREATE TABLE IF NOT EXISTS Status (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(25) UNIQUE
);

CREATE TABLE IF NOT EXISTS Local (
    id SERIAL PRIMARY KEY,
    numero VARCHAR(5),
    lieu VARCHAR(10),
    "type" VARCHAR(3)
);

CREATE TABLE IF NOT EXISTS Personne (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(15),
    prenom VARCHAR(15),
    id_status INTEGER,
    CONSTRAINT fk_status FOREIGN KEY (id_status) REFERENCES Status(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Cours (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(30),
    id_section INTEGER,
    CONSTRAINT fk_cours_section FOREIGN KEY (id_section) REFERENCES Section(id) ON DELETE SET NULL,
    CONSTRAINT unique_cours_per_section UNIQUE (id_section, nom)
);


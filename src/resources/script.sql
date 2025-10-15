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
    "type" VARCHAR(3),
    CONSTRAINT unique_local UNIQUE (numero, lieu)
);

CREATE TABLE IF NOT EXISTS Personne (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(15),
    prenom VARCHAR(15),
    id_status INTEGER,
    CONSTRAINT unique_personne UNIQUE (nom, prenom, id_status),
    CONSTRAINT fk_status FOREIGN KEY (id_status) REFERENCES Status(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Cours (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(30),
    id_section INTEGER,
    CONSTRAINT fk_cours_section FOREIGN KEY (id_section) REFERENCES Section(id) ON DELETE SET NULL,
    CONSTRAINT unique_cours_per_section UNIQUE (id_section, nom)
);

CREATE TABLE IF NOT EXISTS Seance (
    id SERIAL PRIMARY KEY,
    id_cours INTEGER,
    id_local INTEGER,
    date TIMESTAMP,
    CONSTRAINT fk_seance_cours FOREIGN KEY (id_cours) REFERENCES Cours(id) ON DELETE SET NULL,
    CONSTRAINT fk_seance_local FOREIGN KEY (id_local) REFERENCES Local(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Seance_Personne (
    id_personne INT NOT NULL,
    id_seance INT NOT NULL,
    PRIMARY KEY (id_personne, id_seance),
    FOREIGN KEY (id_personne) REFERENCES Personne(id) ON DELETE CASCADE,
    FOREIGN KEY (id_seance) REFERENCES Seance(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Cours_Personne (
    id_personne INT NOT NULL,
    id_cours INT NOT NULL,
    annee_academique DATE,
    PRIMARY KEY (id_personne, id_cours),
    FOREIGN KEY (id_personne) REFERENCES Personne(id) ON DELETE CASCADE,
    FOREIGN KEY (id_cours) REFERENCES Cours(id) ON DELETE CASCADE
);


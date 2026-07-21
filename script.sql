-- Création de la base de données
CREATE DATABASE IF NOT EXISTS gestion_commerciale;
USE gestion_commerciale;

-- Table des clients
CREATE TABLE client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    telephone VARCHAR(20) NOT NULL
);

-- Table des produits
CREATE TABLE produit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL,
    quantite_stock INT NOT NULL DEFAULT 0,
    prix_unitaire DECIMAL(10,2) NOT NULL
);

-- Table des commandes
-- Une commande appartient à un seul client (client_id)
CREATE TABLE commande (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(30) NOT NULL UNIQUE,
    date_commande DATE NOT NULL,
    montant_total DECIMAL(10,2) NOT NULL DEFAULT 0,
    validee BOOLEAN NOT NULL DEFAULT FALSE,
    client_id INT NOT NULL,
    CONSTRAINT fk_commande_client FOREIGN KEY (client_id) REFERENCES client(id)
);

-- Table de liaison commande <-> produit (une commande contient plusieurs produits)
CREATE TABLE ligne_commande (
    id INT AUTO_INCREMENT PRIMARY KEY,
    commande_id INT NOT NULL,
    produit_id INT NOT NULL,
    quantite INT NOT NULL,
    CONSTRAINT fk_ligne_commande FOREIGN KEY (commande_id) REFERENCES commande(id),
    CONSTRAINT fk_ligne_produit FOREIGN KEY (produit_id) REFERENCES produit(id)
);

-- Table des factures
-- Une facture est liée à une seule commande (contrainte UNIQUE sur commande_id)
CREATE TABLE facture (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(30) NOT NULL UNIQUE,
    date_facture DATE NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    commande_id INT NOT NULL UNIQUE,
    CONSTRAINT fk_facture_commande FOREIGN KEY (commande_id) REFERENCES commande(id)
);

-- Table des paiements
-- Une facture peut recevoir plusieurs paiements
CREATE TABLE paiement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(30) NOT NULL UNIQUE,
    date_paiement DATE NOT NULL,
    montant_verse DECIMAL(10,2) NOT NULL,
    facture_id INT NOT NULL,
    CONSTRAINT fk_paiement_facture FOREIGN KEY (facture_id) REFERENCES facture(id)
);
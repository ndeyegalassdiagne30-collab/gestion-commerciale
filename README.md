# Gestion Commerciale - Auchan

Application console Java de gestion commerciale (clients, produits, commandes, factures, paiements) pour le magasin Auchan. Projet réalisé dans le cadre du cours de Génie Logiciel - École Supérieure Professionnelle 221.

## Fonctionnalités

- Gestion des clients (ajout, liste, recherche par téléphone)
- Gestion des produits (ajout, liste, recherche par libellé)
- Gestion des commandes (création avec plusieurs produits, validation)
- Génération automatique de factures après validation d'une commande
- Gestion des paiements (paiement partiel ou total d'une facture)
- Affichage des factures impayées ou partiellement payées

## Architecture

Le projet suit une architecture en couches :

```
src/
├── config/       → Connexion à la base de données (Singleton)
├── entities/     → Classes métier (Client, Produit, Commande, Facture, Paiement, LigneCommande)
├── repository/   → Accès aux données (requêtes SQL)
├── service/      → Logique métier et règles de gestion
├── view/         → Interactions avec l'utilisateur (console)
└── Main.java     → Point d'entrée du programme
```

## Prérequis

- JDK 17 ou supérieur
- MySQL (serveur démarré)
- Le driver JDBC MySQL : `mysql-connector-j-9.1.0.jar`

## Installation

### 1. Cloner le projet

```bash
git clone <url-du-depot>
cd gestion-commerciale
```

### 2. Créer la base de données

Connecte-toi à MySQL et exécute le script SQL fourni dans `script.sql` :

```bash
mysql -u root -p < script.sql
```

Ce script crée la base `gestion_commerciale` ainsi que toutes les tables nécessaires (`client`, `produit`, `commande`, `ligne_commande`, `facture`, `paiement`).

### 3. Télécharger le driver JDBC MySQL

Le driver n'est pas versionné dans le dépôt (voir `.gitignore`). Télécharge-le à la racine du projet :

```bash
wget https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/9.1.0/mysql-connector-j-9.1.0.jar
```

### 4. Configurer la connexion à la base de données

Si besoin, adapte les identifiants de connexion dans `src/config/ConnexionBD.java` :

```java
private static final String URL = "jdbc:mysql://localhost:3306/gestion_commerciale";
private static final String UTILISATEUR = "root";
private static final String MOT_DE_PASSE = "";
```

## Compilation et exécution

Un script est fourni pour compiler et lancer le programme en une seule commande :

```bash
chmod +x compiler.sh   # à faire une seule fois
./compiler.sh
```

Ce script :
1. Supprime les anciens fichiers compilés
2. Recompile tous les fichiers source dans `bin/`
3. Lance directement le programme

## Structure du dépôt

```
gestion-commerciale/
├── compiler.sh                       → Script de compilation + exécution
├── script.sql                        → Script de création de la base de données
├── mysql-connector-j-9.1.0.jar       → Driver JDBC (à télécharger, non versionné)
├── .gitignore
├── README.md
└── src/
    ├── Main.java
    ├── config/
    ├── entities/
    ├── repository/
    ├── service/
    └── view/
```

## Règles de gestion

1. Un client peut effectuer plusieurs commandes.
2. Une commande appartient à un seul client et contient au moins un produit.
3. Une facture est automatiquement créée après validation d'une commande.
4. Une facture est liée à une seule commande.
5. Une facture peut recevoir plusieurs paiements.
6. Le montant total des paiements ne peut jamais dépasser le montant de la facture.
7. Une facture est soldée lorsque la somme des paiements égale son montant.
8. Une commande est considérée comme payée lorsque sa facture est soldée.

## Équipe

Ndeye Galass Diagne
Mamadou Ardo Ndiaye
Seydina Ababacar Ben Thiam
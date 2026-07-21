package view;

import entities.Client;
import entities.Commande;
import entities.Facture;
import entities.Produit;
import service.ClientService;
import service.CommandeService;
import service.FactureService;
import service.PaiementService;
import service.ProduitService;

import java.util.Scanner;

/**
 * Menu principal en deux niveaux :
 * 1er niveau : choix de la gestion (Client, Produit, Commande, Facture, Paiement)
 * 2e niveau  : actions propres à la gestion choisie
 * Tous les services/vues sont chargés en lazy loading (créés seulement à l'usage).
 */
public class MenuView {

    private Scanner scanner = new Scanner(System.in);

    private ClientService clientService;
    private ProduitService produitService;
    private CommandeService commandeService;
    private FactureService factureService;
    private PaiementService paiementService;

    private ClientView clientView;
    private ProduitView produitView;
    private CommandeView commandeView;
    private FactureView factureView;
    private PaiementView paiementView;

    private ClientService getClientService() {
        if (clientService == null) clientService = new ClientService();
        return clientService;
    }

    private ProduitService getProduitService() {
        if (produitService == null) produitService = new ProduitService();
        return produitService;
    }

    private CommandeService getCommandeService() {
        if (commandeService == null) commandeService = new CommandeService();
        return commandeService;
    }

    private FactureService getFactureService() {
        if (factureService == null) factureService = new FactureService();
        return factureService;
    }

    private PaiementService getPaiementService() {
        if (paiementService == null) paiementService = new PaiementService();
        return paiementService;
    }

    private ClientView getClientView() {
        if (clientView == null) clientView = new ClientView();
        return clientView;
    }

    private ProduitView getProduitView() {
        if (produitView == null) produitView = new ProduitView();
        return produitView;
    }

    private CommandeView getCommandeView() {
        if (commandeView == null) commandeView = new CommandeView();
        return commandeView;
    }

    private FactureView getFactureView() {
        if (factureView == null) factureView = new FactureView();
        return factureView;
    }

    private PaiementView getPaiementView() {
        if (paiementView == null) paiementView = new PaiementView();
        return paiementView;
    }

    public void demarrer() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n===== APPLICATION DE GESTION COMMERCIALE - AUCHAN =====");
            System.out.println("1. Gestion des clients");
            System.out.println("2. Gestion des produits");
            System.out.println("3. Gestion des commandes");
            System.out.println("4. Gestion des factures");
            System.out.println("5. Gestion des paiements");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1: menuClient(); break;
                case 2: menuProduit(); break;
                case 3: menuCommande(); break;
                case 4: menuFacture(); break;
                case 5: menuPaiement(); break;
                case 0: System.out.println("Fin du programme. Au revoir !"); break;
                default: System.out.println("Choix invalide.");
            }
        }
    }

    // ----- Sous-menu Client -----
    private void menuClient() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- GESTION DES CLIENTS ---");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Afficher la liste des clients");
            System.out.println("3. Rechercher un client par téléphone");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                    Client client = getClientService().ajouterClient(getClientView().saisirClient());
                    getClientView().afficherMessage(client == null
                            ? "Ce numéro de téléphone est déjà utilisé par un autre client."
                            : "Client ajouté avec succès.");
                    break;
                case 2:
                    getClientView().afficherClients(getClientService().listerClients());
                    break;
                case 3:
                    String tel = getClientView().saisirTelephone();
                    getClientView().afficherClients(getClientService().rechercherParTelephone(tel));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    // ----- Sous-menu Produit -----
    private void menuProduit() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- GESTION DES PRODUITS ---");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Afficher la liste des produits");
            System.out.println("3. Rechercher un produit par libellé");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                    Produit produit = getProduitService().ajouterProduit(getProduitView().saisirProduit());
                    getProduitView().afficherMessage(produit == null
                            ? "Ce libellé existe déjà pour un autre produit."
                            : "Produit ajouté avec succès.");
                    break;
                case 2:
                    getProduitView().afficherProduits(getProduitService().listerProduits());
                    break;
                case 3:
                    String libelle = getProduitView().saisirLibelle();
                    getProduitView().afficherProduits(getProduitService().rechercherParLibelle(libelle));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    // ----- Sous-menu Commande -----
    private void menuCommande() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- GESTION DES COMMANDES ---");
            System.out.println("1. Créer une commande");
            System.out.println("2. Afficher les produits d'une commande");
            System.out.println("3. Afficher la liste des commandes");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                    creerCommande();
                    break;
                case 2:
                    int id = getCommandeView().saisirId();
                    Commande commande = getCommandeService().trouverParId(id);
                    if (commande == null) {
                        getCommandeView().afficherMessage("Commande introuvable.");
                    } else {
                        getCommandeView().afficherLignes(commande);
                    }
                    break;
                case 3:
                    getCommandeView().afficherCommandes(getCommandeService().listerCommandes());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void creerCommande() {
        int idClient = getClientView().saisirId();
        Client client = getClientService().trouverParId(idClient);
        if (client == null) {
            getCommandeView().afficherMessage("Client introuvable.");
            return;
        }

        String numero = getCommandeView().saisirNumero();
        Commande commande = getCommandeService().creerCommande(new Commande(numero, client));
        if (commande == null) {
            getCommandeView().afficherMessage("Ce numéro de commande existe déjà.");
            return;
        }

        boolean continuer = true;
        while (continuer) {
            int idProduit = getCommandeView().saisirIdProduit();
            Produit produit = getProduitService().trouverParId(idProduit);
            if (produit == null) {
                getCommandeView().afficherMessage("Produit introuvable.");
                continue;
            }
            int quantite = getCommandeView().saisirQuantite();
            boolean ok = getCommandeService().ajouterProduitACommande(commande, produit, quantite);
            getCommandeView().afficherMessage(ok ? "Produit ajouté." : "Stock insuffisant.");
            continuer = getCommandeView().demanderAjoutAutreProduit();
        }

        boolean validee = getCommandeService().validerCommande(commande);
        getCommandeView().afficherMessage(validee ? "Commande validée." : "Commande non validée (aucun produit).");
        getCommandeView().afficherCommande(commande);
    }

    // ----- Sous-menu Facture -----
    private void menuFacture() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- GESTION DES FACTURES ---");
            System.out.println("1. Générer une facture pour une commande validée");
            System.out.println("2. Afficher les factures impayées ou partielles");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                    genererFacture();
                    break;
                case 2:
                    getFactureView().afficherFactures(getFactureService().listerFacturesImpayeesOuPartielles());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void genererFacture() {
        int idCommande = getFactureView().saisirIdCommande();
        Commande commande = getCommandeService().trouverParId(idCommande);
        if (commande == null) {
            getFactureView().afficherMessage("Commande introuvable.");
            return;
        }
        String numero = getFactureView().saisirNumero();
        Facture facture = getFactureService().genererFacture(commande, numero);
        if (facture == null) {
            getFactureView().afficherMessage("Impossible de générer la facture : commande non validée ou numéro déjà utilisé.");
        } else {
            getFactureView().afficherFacture(facture);
        }
    }

    // ----- Sous-menu Paiement -----
    private void menuPaiement() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- GESTION DES PAIEMENTS ---");
            System.out.println("1. Enregistrer un paiement");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                    enregistrerPaiement();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void enregistrerPaiement() {
        int idFacture = getPaiementView().saisirIdFacture();
        Facture facture = getFactureService().trouverParId(idFacture);
        if (facture == null) {
            getPaiementView().afficherMessage("Facture introuvable.");
            return;
        }
        getPaiementView().afficherMontantRestant(facture);
        String numero = getPaiementView().saisirNumero();
        double montant = getPaiementView().saisirMontant();

        var paiement = getPaiementService().enregistrerPaiement(facture, numero, montant);
        if (paiement == null) {
            getPaiementView().afficherMessage("Paiement refusé : montant invalide, trop élevé, ou numéro déjà utilisé.");
        } else {
            getPaiementView().afficherMessage("Paiement enregistré.");
            if (facture.estSoldee()) {
                getPaiementView().afficherMessage("Facture soldée. Commande payée.");
            }
        }
    }
}
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

public class MenuView {

    private Scanner scanner = new Scanner(System.in);

    // Aucun objet n'est créé ici. Les champs restent vides tant qu'on ne s'en sert pas.
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

    // Chaque getter crée l'objet une seule fois, au moment où il est réellement demandé
    private ClientService getClientService() {
        if (clientService == null) {
            clientService = new ClientService();
        }
        return clientService;
    }

    private ProduitService getProduitService() {
        if (produitService == null) {
            produitService = new ProduitService();
        }
        return produitService;
    }

    private CommandeService getCommandeService() {
        if (commandeService == null) {
            commandeService = new CommandeService();
        }
        return commandeService;
    }

    private FactureService getFactureService() {
        if (factureService == null) {
            factureService = new FactureService();
        }
        return factureService;
    }

    private PaiementService getPaiementService() {
        if (paiementService == null) {
            paiementService = new PaiementService();
        }
        return paiementService;
    }

    private ClientView getClientView() {
        if (clientView == null) {
            clientView = new ClientView();
        }
        return clientView;
    }

    private ProduitView getProduitView() {
        if (produitView == null) {
            produitView = new ProduitView();
        }
        return produitView;
    }

    private CommandeView getCommandeView() {
        if (commandeView == null) {
            commandeView = new CommandeView();
        }
        return commandeView;
    }

    private FactureView getFactureView() {
        if (factureView == null) {
            factureView = new FactureView();
        }
        return factureView;
    }

    private PaiementView getPaiementView() {
        if (paiementView == null) {
            paiementView = new PaiementView();
        }
        return paiementView;
    }

    public void demarrer() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n===== APPLICATION DE GESTION COMMERCIALE - AUCHAN =====");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Afficher les clients");
            System.out.println("3. Ajouter un produit");
            System.out.println("4. Afficher les produits");
            System.out.println("5. Créer une commande");
            System.out.println("6. Afficher les commandes");
            System.out.println("7. Générer une facture");
            System.out.println("8. Afficher les factures impayées ou partielles");
            System.out.println("9. Enregistrer un paiement");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                    getClientService().ajouterClient(getClientView().saisirClient());
                    break;

                case 2:
                    getClientView().afficherClients(getClientService().listerClients());
                    break;

                case 3:
                    getProduitService().ajouterProduit(getProduitView().saisirProduit());
                    break;

                case 4:
                    getProduitView().afficherProduits(getProduitService().listerProduits());
                    break;

                case 5:
                    creerCommande();
                    break;

                case 6:
                    getCommandeView().afficherCommandes(getCommandeService().listerCommandes());
                    break;

                case 7:
                    genererFacture();
                    break;

                case 8:
                    getFactureView().afficherFactures(getFactureService().listerFacturesImpayeesOuPartielles());
                    break;

                case 9:
                    enregistrerPaiement();
                    break;

                case 0:
                    System.out.println("Fin du programme. Au revoir !");
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
            getFactureView().afficherMessage("La commande n'est pas validée.");
        } else {
            getFactureView().afficherFacture(facture);
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
            getPaiementView().afficherMessage("Paiement refusé : montant invalide ou trop élevé.");
        } else {
            getPaiementView().afficherMessage("Paiement enregistré.");
            if (facture.estSoldee()) {
                getPaiementView().afficherMessage("Facture soldée. Commande payée.");
            }
        }
    }
}
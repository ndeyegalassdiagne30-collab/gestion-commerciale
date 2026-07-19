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
 * Menu principal : construit les services et sous-vues, puis orchestre
 * la saisie (View) et le traitement (Service) pour chaque fonctionnalité.
 */
public class MenuView {

    private Scanner scanner = new Scanner(System.in);

    private ClientService clientService = new ClientService();
    private ProduitService produitService = new ProduitService();
    private CommandeService commandeService = new CommandeService();
    private FactureService factureService = new FactureService();
    private PaiementService paiementService = new PaiementService();

    private ClientView clientView = new ClientView();
    private ProduitView produitView = new ProduitView();
    private CommandeView commandeView = new CommandeView();
    private FactureView factureView = new FactureView();
    private PaiementView paiementView = new PaiementView();

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
            choix = Saisie.lireEntier(scanner, "Choix : ");

            switch (choix) {
                case 1:
                    clientService.ajouterClient(clientView.saisirClient());
                    break;

                case 2:
                    clientView.afficherClients(clientService.listerClients());
                    break;

                case 3:
                    produitService.ajouterProduit(produitView.saisirProduit());
                    break;

                case 4:
                    produitView.afficherProduits(produitService.listerProduits());
                    break;

                case 5:
                    creerCommande();
                    break;

                case 6:
                    commandeView.afficherCommandes(commandeService.listerCommandes());
                    break;

                case 7:
                    genererFacture();
                    break;

                case 8:
                    factureView.afficherFactures(factureService.listerFacturesImpayeesOuPartielles());
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
        int idClient = clientView.saisirId();
        Client client = clientService.trouverParId(idClient);
        if (client == null) {
            commandeView.afficherMessage("Client introuvable.");
            return;
        }

        String numero = commandeView.saisirNumero();
        Commande commande = commandeService.creerCommande(new Commande(numero, client));

        boolean continuer = true;
        while (continuer) {
            int idProduit = commandeView.saisirIdProduit();
            Produit produit = produitService.trouverParId(idProduit);
            if (produit == null) {
                commandeView.afficherMessage("Produit introuvable.");
                continue;
            }
            int quantite = commandeView.saisirQuantite();
            boolean ok = commandeService.ajouterProduitACommande(commande, produit, quantite);
            commandeView.afficherMessage(ok ? "Produit ajouté." : "Stock insuffisant.");
            continuer = commandeView.demanderAjoutAutreProduit();
        }

        boolean validee = commandeService.validerCommande(commande);
        commandeView.afficherMessage(validee ? "Commande validée." : "Commande non validée (aucun produit).");
        commandeView.afficherCommande(commande);
    }

    private void genererFacture() {
        int idCommande = factureView.saisirIdCommande();
        Commande commande = commandeService.trouverParId(idCommande);
        if (commande == null) {
            factureView.afficherMessage("Commande introuvable.");
            return;
        }
        String numero = factureView.saisirNumero();
        Facture facture = factureService.genererFacture(commande, numero);
        if (facture == null) {
            factureView.afficherMessage("La commande n'est pas validée.");
        } else {
            factureView.afficherFacture(facture);
        }
    }

    private void enregistrerPaiement() {
        int idFacture = paiementView.saisirIdFacture();
        Facture facture = factureService.trouverParId(idFacture);
        if (facture == null) {
            paiementView.afficherMessage("Facture introuvable.");
            return;
        }
        paiementView.afficherMontantRestant(facture);
        String numero = paiementView.saisirNumero();
        double montant = paiementView.saisirMontant();

        var paiement = paiementService.enregistrerPaiement(facture, numero, montant);
        if (paiement == null) {
            paiementView.afficherMessage("Paiement refusé : montant invalide ou trop élevé.");
        } else {
            paiementView.afficherMessage("Paiement enregistré.");
            if (facture.estSoldee()) {
                paiementView.afficherMessage("Facture soldée. Commande payée.");
            }
        }
    }
}
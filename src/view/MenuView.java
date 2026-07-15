package src.view;

import java.util.Scanner;

/**
 * Menu principal de l'application. Redirige vers chaque sous-menu (client,
 * produit, commande, facture, paiement).
 */
public class MenuView {

    private ClientView clientView;
    private ProduitView produitView;
    private CommandeView commandeView;
    private FactureView factureView;
    private PaiementView paiementView;
    private Scanner scanner;

    public MenuView(ClientView clientView, ProduitView produitView, CommandeView commandeView,
                     FactureView factureView, PaiementView paiementView, Scanner scanner) {
        this.clientView = clientView;
        this.produitView = produitView;
        this.commandeView = commandeView;
        this.factureView = factureView;
        this.paiementView = paiementView;
        this.scanner = scanner;
    }

    /**
     * Lance la boucle principale de l'application console.
     */
    public void demarrer() {
        boolean quitter = false;
        while (!quitter) {
            System.out.println("\n===== APPLICATION DE GESTION COMMERCIALE - AUCHAN =====");
            System.out.println("1. Gestion des clients");
            System.out.println("2. Gestion des produits");
            System.out.println("3. Gestion des commandes");
            System.out.println("4. Gestion des factures");
            System.out.println("5. Gestion des paiements");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    clientView.afficherMenu();
                    break;
                case "2":
                    produitView.afficherMenu();
                    break;
                case "3":
                    commandeView.afficherMenu();
                    break;
                case "4":
                    factureView.afficherMenu();
                    break;
                case "5":
                    paiementView.afficherMenu();
                    break;
                case "0":
                    quitter = true;
                    System.out.println("Fin du programme. Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
}
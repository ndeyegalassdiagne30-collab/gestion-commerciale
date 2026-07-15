package src.view;

import src.entities.Produit;
import src.service.ProduitService;
import java.util.List;
import java.util.Scanner;

/**
 * Gère les interactions console pour la gestion des produits.
 */
public class ProduitView {

    private ProduitService produitService;
    private Scanner scanner;

    public ProduitView(ProduitService produitService, Scanner scanner) {
        this.produitService = produitService;
        this.scanner = scanner;
    }

    public void afficherMenu() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n--- GESTION DES PRODUITS ---");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Afficher la liste des produits");
            System.out.println("3. Rechercher un produit par libellé");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    ajouterProduit();
                    break;
                case "2":
                    afficherProduits();
                    break;
                case "3":
                    rechercherParLibelle();
                    break;
                case "0":
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void ajouterProduit() {
        System.out.print("Libellé : ");
        String libelle = scanner.nextLine();
        System.out.print("Quantité en stock : ");
        int quantite = Integer.parseInt(scanner.nextLine());
        System.out.print("Prix unitaire : ");
        double prix = Double.parseDouble(scanner.nextLine());

        Produit produit = produitService.ajouterProduit(libelle, quantite, prix);
        System.out.println("Produit ajouté : " + produit.toChaine());
    }

    private void afficherProduits() {
        List<Produit> produits = produitService.listerProduits();
        if (produits.isEmpty()) {
            System.out.println("Aucun produit enregistré.");
            return;
        }
        for (Produit produit : produits) {
            System.out.println(produit.toChaine());
        }
    }

    private void rechercherParLibelle() {
        System.out.print("Libellé à rechercher : ");
        String libelle = scanner.nextLine();
        List<Produit> resultats = produitService.rechercherParLibelle(libelle);
        if (resultats.isEmpty()) {
            System.out.println("Aucun produit trouvé.");
            return;
        }
        for (Produit produit : resultats) {
            System.out.println(produit.toChaine());
        }
    }
}
package view;

import entities.Produit;
import java.util.List;
import java.util.Scanner;

public class ProduitView {

    private Scanner scanner = new Scanner(System.in);

    public Produit saisirProduit() {
        System.out.print("Libellé : ");
        String libelle = scanner.nextLine();
        int quantite = Saisie.lireEntierPositifOuNul(scanner, "Quantité en stock : ");
        double prix = Saisie.lireDoublePositifOuNul(scanner, "Prix unitaire : ");
        return new Produit(libelle, quantite, prix);
    }

    public int saisirId() {
        return Saisie.lireEntier(scanner, "Id du produit : ");
    }

    public String saisirLibelle() {
        System.out.print("Libellé à rechercher : ");
        return scanner.nextLine();
    }

    public void afficherProduits(List<Produit> produits) {
        if (produits.isEmpty()) {
            System.out.println("Aucun produit enregistré.");
            return;
        }
        for (Produit produit : produits) {
            System.out.println(produit.toChaine());
        }
    }
}
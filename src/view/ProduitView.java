package src.view;

import src.entities.Produit;
import java.util.List;
import java.util.Scanner;

public class ProduitView {

    private Scanner scanner = new Scanner(System.in);

    public Produit saisirProduit() {
        System.out.print("Libellé : ");
        String libelle = scanner.nextLine();
        System.out.print("Quantité en stock : ");
        int quantite = Integer.parseInt(scanner.nextLine());
        System.out.print("Prix unitaire : ");
        double prix = Double.parseDouble(scanner.nextLine());
        return new Produit(libelle, quantite, prix);
    }

    public int saisirId() {
        System.out.print("Id du produit : ");
        return Integer.parseInt(scanner.nextLine());
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
package view;

import entities.Produit;
import utils.Validateur;
import java.util.List;
import java.util.Scanner;

public class ProduitView {

    private Console console = new Console(new Scanner(System.in));

    public Produit saisirProduit() {
        String libelle = console.lireChaineNonVide("Libellé : ");
        int quantite = console.lireEntier("Quantité en stock (min " + Validateur.QUANTITE_MIN + ") : ",
                Validateur.QUANTITE_MIN, Validateur.QUANTITE_MAX);
        double prix = console.lireDouble("Prix unitaire : ", Validateur.PRIX_MIN, Validateur.PRIX_MAX);
        return new Produit(libelle, quantite, prix);
    }

    public int saisirId() {
        return console.lireEntier("Id du produit : ", 1, Validateur.ID_MAX);
    }

    public String saisirLibelle() {
        return console.lireChaineNonVide("Libellé à rechercher : ");
    }

    public void afficherMessage(String message) {
        System.out.println(message);
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
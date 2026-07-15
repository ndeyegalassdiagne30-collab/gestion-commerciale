package src.repository;

import src.entities.Produit;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère le stockage et la récupération des produits en mémoire.
 */
public class ProduitRepository {

    private List<Produit> produits = new ArrayList<>();
    private int prochainId = 1;

    public Produit ajouter(String libelle, int quantiteEnStock, double prixUnitaire) {
        Produit produit = new Produit(prochainId, libelle, quantiteEnStock, prixUnitaire);
        produits.add(produit);
        prochainId++;
        return produit;
    }

    public List<Produit> getTous() {
        return produits;
    }

    public Produit trouverParId(int id) {
        for (Produit produit : produits) {
            if (produit.getId() == id) {
                return produit;
            }
        }
        return null;
    }

    /**
     * Recherche (bonus) : trouve les produits dont le libellé
     * contient le texte recherché (insensible à la casse).
     */
    public List<Produit> rechercherParLibelle(String libelle) {
        List<Produit> resultat = new ArrayList<>();
        for (Produit produit : produits) {
            if (produit.getLibelle().toLowerCase().contains(libelle.toLowerCase())) {
                resultat.add(produit);
            }
        }
        return resultat;
    }
}
package service;

import entities.Produit;
import repository.ProduitRepository;
import java.util.List;

public class ProduitService {

    private ProduitRepository produitRepository = new ProduitRepository();

    /**
     * Ajoute un produit, sauf si un produit existe déjà avec le même libellé (doublon).
     */
    public Produit ajouterProduit(Produit produit) {
        if (produitRepository.existeParLibelle(produit.getLibelle())) {
            return null;
        }
        return produitRepository.ajouter(produit);
    }

    public List<Produit> listerProduits() {
        return produitRepository.getTous();
    }

    public Produit trouverParId(int id) {
        return produitRepository.trouverParId(id);
    }

    public List<Produit> rechercherParLibelle(String libelle) {
        return produitRepository.rechercherParLibelle(libelle);
    }
}
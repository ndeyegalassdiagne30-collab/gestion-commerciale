package src.service;

import src.entities.Produit;
import src.repository.ProduitRepository;
import java.util.List;

/**
 * Contient la logique métier liée aux produits.
 */
public class ProduitService {

    private ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public Produit ajouterProduit(String libelle, int quantiteEnStock, double prixUnitaire) {
        return produitRepository.ajouter(libelle, quantiteEnStock, prixUnitaire);
    }

    public List<Produit> listerProduits() {
        return produitRepository.getTous();
    }

    public Produit trouverParId(int id) {
        return produitRepository.trouverParId(id);
    }

    // Bonus : recherche d'un produit par libellé
    public List<Produit> rechercherParLibelle(String libelle) {
        return produitRepository.rechercherParLibelle(libelle);
    }
}
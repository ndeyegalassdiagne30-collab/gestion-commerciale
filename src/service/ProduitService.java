
package service;

import src.entities.Produit;
import repository.ProduitRepository;
import java.util.List;

public class ProduitService {

    private ProduitRepository produitRepository = new ProduitRepository();

    public Produit ajouterProduit(Produit produit) {
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
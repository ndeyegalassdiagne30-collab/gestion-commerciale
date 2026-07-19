package service;

import entities.Commande;
import entities.LigneCommande;
import entities.Produit;
import repository.CommandeRepository;
import repository.ProduitRepository;
import java.util.List;

/**
 * Contient la logique métier liée aux commandes.
 */
public class CommandeService {

    private CommandeRepository commandeRepository = new CommandeRepository();
    private ProduitRepository produitRepository = new ProduitRepository();

    public Commande creerCommande(Commande commande) {
        return commandeRepository.ajouter(commande);
    }

    /**
     * Ajoute un produit à une commande : vérifie le stock, le décrémente,
     * enregistre la ligne en base et met à jour le montant total.
     * Retourne false si le stock est insuffisant.
     */
    public boolean ajouterProduitACommande(Commande commande, Produit produit, int quantite) {
        if (quantite > produit.getQuantiteEnStock()) {
            return false;
        }
        produit.setQuantiteEnStock(produit.getQuantiteEnStock() - quantite);
        produitRepository.mettreAJourStock(produit);

        LigneCommande ligne = new LigneCommande(produit, quantite);
        commande.ajouterLigne(ligne);
        commandeRepository.ajouterLigne(commande, ligne);
        return true;
    }

    public boolean validerCommande(Commande commande) {
        if (commande.getLignes().isEmpty()) {
            return false;
        }
        commande.setValidee(true);
        commandeRepository.valider(commande);
        return true;
    }

    public List<Commande> listerCommandes() {
        return commandeRepository.getTous();
    }

    public Commande trouverParId(int id) {
        return commandeRepository.trouverParId(id);
    }
}

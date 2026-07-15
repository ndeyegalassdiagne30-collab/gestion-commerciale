package src.service;

import src.entities.Client;
import src.entities.Commande;
import src.entities.LigneCommande;
import src.entities.Produit;
import src.repository.CommandeRepository;
import java.util.List;

/**
 * Contient la logique métier liée aux commandes.
 * Règle de gestion : un client peut passer plusieurs commandes,
 * une commande appartient à un seul client et contient au moins un produit.
 */
public class CommandeService {

    private CommandeRepository commandeRepository;

    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    /**
     * Crée une nouvelle commande vide pour un client.
     * Il faudra ensuite y ajouter des lignes avec ajouterProduitACommande().
     */
    public Commande creerCommande(String numero, Client client) {
        return commandeRepository.ajouter(numero, client);
    }

    /**
     * Ajoute un produit à une commande et retire la quantité correspondante du stock.
     * Retourne false si le stock est insuffisant.
     */
    public boolean ajouterProduitACommande(Commande commande, Produit produit, int quantite) {
        if (!produit.retirerDuStock(quantite)) {
            return false;
        }
        commande.ajouterLigne(new LigneCommande(produit, quantite));
        return true;
    }

    /**
     * Valide une commande. Une commande doit contenir au moins un produit
     * pour pouvoir être validée.
     */
    public boolean validerCommande(Commande commande) {
        if (commande.getLignes().isEmpty()) {
            return false;
        }
        commande.setValidee(true);
        return true;
    }

    public List<Commande> listerCommandes() {
        return commandeRepository.getTous();
    }

    public Commande trouverParId(int id) {
        return commandeRepository.trouverParId(id);
    }

    public List<Commande> trouverParClient(Client client) {
        return commandeRepository.trouverParClient(client);
    }
}
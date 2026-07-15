package src.repository;

import src.entities.Client;
import src.entities.Commande;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère le stockage et la récupération des commandes en mémoire.
 */
public class CommandeRepository {

    private List<Commande> commandes = new ArrayList<>();
    private int prochainId = 1;

    public Commande ajouter(String numero, Client client) {
        Commande commande = new Commande(prochainId, numero, LocalDate.now(), client);
        commandes.add(commande);
        prochainId++;
        return commande;
    }

    public List<Commande> getTous() {
        return commandes;
    }

    public Commande trouverParId(int id) {
        for (Commande commande : commandes) {
            if (commande.getId() == id) {
                return commande;
            }
        }
        return null;
    }

    /**
     * Retourne toutes les commandes d'un client donné.
     */
    public List<Commande> trouverParClient(Client client) {
        List<Commande> resultat = new ArrayList<>();
        for (Commande commande : commandes) {
            if (commande.getClient().getId() == client.getId()) {
                resultat.add(commande);
            }
        }
        return resultat;
    }
}
package src.repository;

import src.entities.Client;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère le stockage et la récupération des clients en mémoire.
 */
public class ClientRepository {

    private List<Client> clients = new ArrayList<>();
    private int prochainId = 1;

    /**
     * Ajoute un nouveau client et lui attribue automatiquement un id.
     */
    public Client ajouter(String nom, String prenom, String telephone) {
        Client client = new Client(prochainId, nom, prenom, telephone);
        clients.add(client);
        prochainId++;
        return client;
    }

    public List<Client> getTous() {
        return clients;
    }

    public Client trouverParId(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    /**
     * Recherche (bonus) : trouve les clients dont le numéro de téléphone
     * contient la chaîne recherchée.
     */
    public List<Client> rechercherParTelephone(String telephone) {
        List<Client> resultat = new ArrayList<>();
        for (Client client : clients) {
            if (client.getTelephone().contains(telephone)) {
                resultat.add(client);
            }
        }
        return resultat;
    }
}
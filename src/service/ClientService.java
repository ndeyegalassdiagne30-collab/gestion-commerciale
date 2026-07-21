package service;

import entities.Client;
import repository.ClientRepository;
import java.util.List;

public class ClientService {

    private ClientRepository clientRepository = new ClientRepository();

    /**
     * Ajoute un client, sauf si un client existe déjà avec le même téléphone (doublon).
     * Retourne null si le doublon est détecté.
     */
    public Client ajouterClient(Client client) {
        if (clientRepository.existeParTelephone(client.getTelephone())) {
            return null;
        }
        return clientRepository.ajouter(client);
    }

    public List<Client> listerClients() {
        return clientRepository.getTous();
    }

    public Client trouverParId(int id) {
        return clientRepository.trouverParId(id);
    }

    public List<Client> rechercherParTelephone(String telephone) {
        return clientRepository.rechercherParTelephone(telephone);
    }
}
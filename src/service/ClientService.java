package src.service;

import src.entities.Client;
import src.repository.ClientRepository;
import java.util.List;

/**
 * Contient la logique métier liée aux clients.
 */
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client ajouterClient(String nom, String prenom, String telephone) {
        return clientRepository.ajouter(nom, prenom, telephone);
    }

    public List<Client> listerClients() {
        return clientRepository.getTous();
    }

    public Client trouverParId(int id) {
        return clientRepository.trouverParId(id);
    }

    // Bonus : recherche d'un client par téléphone
    public List<Client> rechercherParTelephone(String telephone) {
        return clientRepository.rechercherParTelephone(telephone);
    }
}
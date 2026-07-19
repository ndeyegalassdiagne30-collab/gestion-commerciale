

package service;

import entities.Client;
import repository.ClientRepository;
import java.util.List;

public class ClientService {

    private ClientRepository clientRepository = new ClientRepository();

    public Client ajouterClient(Client client) {
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
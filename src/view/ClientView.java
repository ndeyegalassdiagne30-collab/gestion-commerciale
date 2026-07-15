package src.view;

import src.entities.Client;
import src.service.ClientService;
import java.util.List;
import java.util.Scanner;

/**
 * Gère les interactions console pour la gestion des clients.
 */
public class ClientView {

    private ClientService clientService;
    private Scanner scanner;

    public ClientView(ClientService clientService, Scanner scanner) {
        this.clientService = clientService;
        this.scanner = scanner;
    }

    public void afficherMenu() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n--- GESTION DES CLIENTS ---");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Afficher la liste des clients");
            System.out.println("3. Rechercher un client par téléphone");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    ajouterClient();
                    break;
                case "2":
                    afficherClients();
                    break;
                case "3":
                    rechercherParTelephone();
                    break;
                case "0":
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void ajouterClient() {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();

        Client client = clientService.ajouterClient(nom, prenom, telephone);
        System.out.println("Client ajouté : " + client);
    }

    private void afficherClients() {
        List<Client> clients = clientService.listerClients();
        if (clients.isEmpty()) {
            System.out.println("Aucun client enregistré.");
            return;
        }
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    private void rechercherParTelephone() {
        System.out.print("Téléphone à rechercher : ");
        String telephone = scanner.nextLine();
        List<Client> resultats = clientService.rechercherParTelephone(telephone);
        if (resultats.isEmpty()) {
            System.out.println("Aucun client trouvé.");
            return;
        }
        for (Client client : resultats) {
            System.out.println(client);
        }
    }
}
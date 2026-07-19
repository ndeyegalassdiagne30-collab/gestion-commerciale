package src.view;

import src.entities.Client;
import java.util.List;
import java.util.Scanner;

public class ClientView {

    private Scanner scanner = new Scanner(System.in);

    public Client saisirClient() {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();
        return new Client(nom, prenom, telephone);
    }

    public int saisirId() {
        System.out.print("Id du client : ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String saisirTelephone() {
        System.out.print("Téléphone à rechercher : ");
        return scanner.nextLine();
    }

    public void afficherClients(List<Client> clients) {
        if (clients.isEmpty()) {
            System.out.println("Aucun client enregistré.");
            return;
        }
        for (Client client : clients) {
            System.out.println(client.toChaine());
        }
    }
}
package view;

import entities.Client;
import utils.Validateur;
import java.util.List;
import java.util.Scanner;

public class ClientView {

    private Console console = new Console(new Scanner(System.in));

    public Client saisirClient() {
        String nom = console.lireChaineNonVide("Nom : ");
        String prenom = console.lireChaineNonVide("Prénom : ");
        String telephone = console.lireTelephoneOrange("Téléphone (ex: 771234567) : ");
        return new Client(nom, prenom, telephone);
    }

    public int saisirId() {
        return console.lireEntier("Id du client : ", 1, Validateur.ID_MAX);
    }

    public String saisirTelephone() {
        return console.lireChaineNonVide("Téléphone à rechercher : ");
    }

    public void afficherMessage(String message) {
        System.out.println(message);
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
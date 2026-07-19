package view;

import entities.Facture;
import java.util.List;
import java.util.Scanner;

public class FactureView {

    private Scanner scanner = new Scanner(System.in);

    public int saisirIdCommande() {
        return Saisie.lireEntier(scanner, "Id de la commande : ");
    }

    public String saisirNumero() {
        System.out.print("Numéro de la facture : ");
        return scanner.nextLine();
    }

    public int saisirId() {
        return Saisie.lireEntier(scanner, "Id de la facture : ");
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    public void afficherFacture(Facture facture) {
        if (facture == null) {
            System.out.println("Facture introuvable.");
        } else {
            System.out.println(facture.toChaine());
        }
    }

    public void afficherFactures(List<Facture> factures) {
        if (factures.isEmpty()) {
            System.out.println("Aucune facture à afficher.");
            return;
        }
        for (Facture facture : factures) {
            System.out.println(facture.toChaine());
        }
    }
}
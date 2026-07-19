package src.view;

import src.entities.Facture;
import java.util.List;
import java.util.Scanner;

public class FactureView {

    private Scanner scanner = new Scanner(System.in);

    public int saisirIdCommande() {
        System.out.print("Id de la commande : ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String saisirNumero() {
        System.out.print("Numéro de la facture : ");
        return scanner.nextLine();
    }

    public int saisirId() {
        System.out.print("Id de la facture : ");
        return Integer.parseInt(scanner.nextLine());
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
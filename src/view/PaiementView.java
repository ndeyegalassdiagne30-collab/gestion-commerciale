package src.view;

import src.entities.Facture;
import java.util.Scanner;

public class PaiementView {

    private Scanner scanner = new Scanner(System.in);

    public int saisirIdFacture() {
        System.out.print("Id de la facture : ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String saisirNumero() {
        System.out.print("Numéro du paiement : ");
        return scanner.nextLine();
    }

    public double saisirMontant() {
        System.out.print("Montant versé : ");
        return Double.parseDouble(scanner.nextLine());
    }

    public void afficherMontantRestant(Facture facture) {
        System.out.println("Montant restant à payer : " + facture.getMontantRestant() + " FCFA");
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
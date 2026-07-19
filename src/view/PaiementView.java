package view;

import entities.Facture;
import java.util.Scanner;

public class PaiementView {

    private Scanner scanner = new Scanner(System.in);

    public int saisirIdFacture() {
        return Saisie.lireEntier(scanner, "Id de la facture : ");
    }

    public String saisirNumero() {
        System.out.print("Numéro du paiement : ");
        return scanner.nextLine();
    }

    public double saisirMontant() {
        return Saisie.lireDoubleStrictementPositif(scanner, "Montant versé : ");
    }

    public void afficherMontantRestant(Facture facture) {
        System.out.println("Montant restant à payer : " + facture.getMontantRestant() + " FCFA");
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
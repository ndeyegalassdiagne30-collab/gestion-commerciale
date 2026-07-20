package view;

import entities.Facture;
import utils.Validateur;
import java.util.Scanner;

public class PaiementView {

    private Console console = new Console(new Scanner(System.in));

    public int saisirIdFacture() {
        return console.lireEntier("Id de la facture : ", 1, Validateur.ID_MAX);
    }

    public String saisirNumero() {
        return console.lireChaineNonVide("Numéro du paiement : ");
    }

    public double saisirMontant() {
        return console.lireDouble("Montant versé : ", Validateur.MONTANT_MIN, Validateur.MONTANT_MAX);
    }

    public void afficherMontantRestant(Facture facture) {
        System.out.println("Montant restant à payer : " + facture.getMontantRestant() + " FCFA");
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
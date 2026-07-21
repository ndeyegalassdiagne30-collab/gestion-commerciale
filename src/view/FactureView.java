package view;

import entities.Facture;
import utils.Validateur;
import java.util.List;
import java.util.Scanner;

public class FactureView {

    private Console console = new Console(new Scanner(System.in));

    public int saisirIdCommande() {
        return console.lireEntier("Id de la commande : ", 1, Validateur.ID_MAX);
    }

    public String saisirNumero() {
        return console.lireChaineNonVide("Numéro de la facture : ");
    }

    public int saisirId() {
        return console.lireEntier("Id de la facture : ", 1, Validateur.ID_MAX);
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
package view;

import entities.Commande;
import java.util.List;
import java.util.Scanner;

public class CommandeView {

    private Scanner scanner = new Scanner(System.in);

    public String saisirNumero() {
        System.out.print("Numéro de la commande : ");
        return scanner.nextLine();
    }

    public int saisirId() {
        return Saisie.lireEntier(scanner, "Id de la commande : ");
    }

    public int saisirIdProduit() {
        return Saisie.lireEntier(scanner, "Id du produit à ajouter : ");
    }

    public int saisirQuantite() {
        return Saisie.lireEntierStrictementPositif(scanner, "Quantité : ");
    }

    public boolean demanderAjoutAutreProduit() {
        System.out.print("Ajouter un autre produit ? (o/n) : ");
        return scanner.nextLine().equalsIgnoreCase("o");
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    public void afficherCommande(Commande commande) {
        if (commande == null) {
            System.out.println("Commande introuvable.");
        } else {
            System.out.println(commande.toChaine());
        }
    }

    public void afficherCommandes(List<Commande> commandes) {
        if (commandes.isEmpty()) {
            System.out.println("Aucune commande enregistrée.");
            return;
        }
        for (Commande commande : commandes) {
            System.out.println(commande.toChaine());
        }
    }

    public void afficherLignes(Commande commande) {
        if (commande.getLignes().isEmpty()) {
            System.out.println("Aucun produit dans cette commande.");
            return;
        }
        commande.getLignes().forEach(ligne -> System.out.println(ligne.toChaine()));
    }
}
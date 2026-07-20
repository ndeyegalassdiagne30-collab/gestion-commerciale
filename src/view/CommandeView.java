package view;

import entities.Commande;
import utils.Validateur;
import java.util.List;
import java.util.Scanner;

public class CommandeView {

    private Console console = new Console(new Scanner(System.in));

    public String saisirNumero() {
        return console.lireChaineNonVide("Numéro de la commande : ");
    }

    public int saisirId() {
        return console.lireEntier("Id de la commande : ", 1, Validateur.ID_MAX);
    }

    public int saisirIdProduit() {
        return console.lireEntier("Id du produit à ajouter : ", 1, Validateur.ID_MAX);
    }

    public int saisirQuantite() {
        return console.lireEntier("Quantité : ", Validateur.QUANTITE_MIN, Validateur.QUANTITE_MAX);
    }

    public boolean demanderAjoutAutreProduit() {
        String reponse = console.lireLigne("Ajouter un autre produit ? (o/n) : ");
        return reponse.equalsIgnoreCase("o");
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
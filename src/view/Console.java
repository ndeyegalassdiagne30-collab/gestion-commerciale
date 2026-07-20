package view;

import utils.Validateur;
import java.util.Scanner;

/**
 * Centralise la saisie console avec validation.
 * Redemande automatiquement tant que la saisie n'est pas valide,
 * au lieu de planter ou d'accepter n'importe quoi.
 */
public class Console {

    private Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public String lireChaineNonVide(String message) {
        String valeur;
        do {
            System.out.print(message);
            valeur = scanner.nextLine().trim();
            if (!Validateur.estNonVide(valeur)) {
                System.out.println("Ce champ ne peut pas être vide.");
            }
        } while (!Validateur.estNonVide(valeur));
        return valeur;
    }

    public int lireEntier(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine().trim();
            try {
                int valeur = Integer.parseInt(saisie);
                if (!Validateur.estEntierDansBornes(valeur, min, max)) {
                    System.out.println("Veuillez saisir un entier entre " + min + " et " + max + ".");
                    continue;
                }
                return valeur;
            } catch (NumberFormatException e) {
                System.out.println("Saisie invalide : un nombre entier est attendu.");
            }
        }
    }

    public double lireDouble(String message, double min, double max) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine().trim();
            try {
                double valeur = Double.parseDouble(saisie);
                if (!Validateur.estDoubleDansBornes(valeur, min, max)) {
                    System.out.println("Veuillez saisir un nombre entre " + min + " et " + max + ".");
                    continue;
                }
                return valeur;
            } catch (NumberFormatException e) {
                System.out.println("Saisie invalide : un nombre décimal est attendu.");
            }
        }
    }

    public String lireTelephoneOrange(String message) {
        String telephone;
        do {
            System.out.print(message);
            telephone = scanner.nextLine().trim();
            if (!Validateur.estTelephoneOrangeValide(telephone)) {
                System.out.println("Numéro invalide. Format attendu : 77XXXXXXX ou 78XXXXXXX (Orange Sénégal).");
            }
        } while (!Validateur.estTelephoneOrangeValide(telephone));
        return telephone;
    }

    public String lireLigne(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
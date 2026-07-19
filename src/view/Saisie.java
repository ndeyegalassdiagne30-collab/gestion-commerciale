package src.view;

import java.util.Scanner;

/**
 * redemande tant que la valeur saisie n'est
 * pas un nombre valide.
 */
class Saisie {

    static int lireEntier(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String texte = scanner.nextLine();
            try {
                return Integer.parseInt(texte);
            } catch (NumberFormatException e) {
                System.out.println("Valeur invalide, veuillez saisir un nombre entier.");
            }
        }
    }

    static double lireDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String texte = scanner.nextLine();
            try {
                return Double.parseDouble(texte);
            } catch (NumberFormatException e) {
                System.out.println("Valeur invalide, veuillez saisir un nombre.");
            }
        }
    }

    static int lireEntierPositifOuNul(Scanner scanner, String message) {
        while (true) {
            int valeur = lireEntier(scanner, message);
            if (valeur < 0) {
                System.out.println("Valeur invalide, veuillez saisir un nombre positif ou nul.");
                continue;
            }
            return valeur;
        }
    }

    static int lireEntierStrictementPositif(Scanner scanner, String message) {
        while (true) {
            int valeur = lireEntier(scanner, message);
            if (valeur <= 0) {
                System.out.println("Valeur invalide, veuillez saisir un nombre strictement positif.");
                continue;
            }
            return valeur;
        }
    }

    static double lireDoublePositifOuNul(Scanner scanner, String message) {
        while (true) {
            double valeur = lireDouble(scanner, message);
            if (valeur < 0) {
                System.out.println("Valeur invalide, veuillez saisir un nombre positif ou nul.");
                continue;
            }
            return valeur;
        }
    }
}

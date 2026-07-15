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
}

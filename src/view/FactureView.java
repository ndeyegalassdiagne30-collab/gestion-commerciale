package src.view;

import src.entities.Commande;
import src.entities.Facture;
import src.service.CommandeService;
import src.service.FactureService;
import java.util.List;
import java.util.Scanner;

/**
 * Gère les interactions console pour la gestion des factures.
 */
public class FactureView {

    private FactureService factureService;
    private CommandeService commandeService;
    private Scanner scanner;

    public FactureView(FactureService factureService, CommandeService commandeService, Scanner scanner) {
        this.factureService = factureService;
        this.commandeService = commandeService;
        this.scanner = scanner;
    }

    public void afficherMenu() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n--- GESTION DES FACTURES ---");
            System.out.println("1. Générer une facture pour une commande validée");
            System.out.println("2. Afficher la facture d'une commande");
            System.out.println("3. Afficher les factures impayées ou partielles");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    genererFacture();
                    break;
                case "2":
                    afficherFactureDeCommande();
                    break;
                case "3":
                    afficherFacturesImpayees();
                    break;
                case "0":
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void genererFacture() {
        int id = Saisie.lireEntier(scanner, "Id de la commande : ");
        Commande commande = commandeService.trouverParId(id);
        if (commande == null) {
            System.out.println("Commande introuvable.");
            return;
        }
        System.out.print("Numéro de la facture : ");
        String numero = scanner.nextLine();

        Facture facture = factureService.genererFacture(commande, numero);
        if (facture == null) {
            System.out.println("Impossible de générer la facture : la commande n'est pas validée.");
        } else {
            System.out.println("Facture générée : " + facture.toChaine());
        }
    }

    private void afficherFactureDeCommande() {
        int id = Saisie.lireEntier(scanner, "Id de la commande : ");
        Commande commande = commandeService.trouverParId(id);
        if (commande == null) {
            System.out.println("Commande introuvable.");
            return;
        }
        Facture facture = factureService.trouverParCommande(commande);
        if (facture == null) {
            System.out.println("Aucune facture pour cette commande.");
            return;
        }
        System.out.println(facture.toChaine());
    }

    private void afficherFacturesImpayees() {
        List<Facture> factures = factureService.listerFacturesImpayeesOuPartielles();
        if (factures.isEmpty()) {
            System.out.println("Toutes les factures sont soldées.");
            return;
        }
        for (Facture facture : factures) {
            System.out.println(facture.toChaine());
        }
    }
}
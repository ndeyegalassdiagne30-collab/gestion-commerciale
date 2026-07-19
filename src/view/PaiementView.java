package src.view;

import src.entities.Facture;
import src.entities.Paiement;
import src.service.FactureService;
import src.service.PaiementService;
import java.util.List;
import java.util.Scanner;

/**
 * Gère les interactions console pour la gestion des paiements.
 */
public class PaiementView {

    private PaiementService paiementService;
    private FactureService factureService;
    private Scanner scanner;

    public PaiementView(PaiementService paiementService, FactureService factureService, Scanner scanner) {
        this.paiementService = paiementService;
        this.factureService = factureService;
        this.scanner = scanner;
    }

    public void afficherMenu() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n--- GESTION DES PAIEMENTS ---");
            System.out.println("1. Enregistrer un paiement");
            System.out.println("2. Afficher les paiements d'une facture");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    enregistrerPaiement();
                    break;
                case "2":
                    afficherPaiementsDeFacture();
                    break;
                case "0":
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void enregistrerPaiement() {
        int id = Saisie.lireEntier(scanner, "Id de la facture : ");
        Facture facture = factureService.trouverParId(id);
        if (facture == null) {
            System.out.println("Facture introuvable.");
            return;
        }

        System.out.println("Montant restant à payer : " + facture.getMontantRestant() + " FCFA");
        System.out.print("Numéro du paiement : ");
        String numero = scanner.nextLine();
        double montant = Saisie.lireDouble(scanner, "Montant versé : ");

        Paiement paiement = paiementService.enregistrerPaiement(facture, numero, montant);
        if (paiement == null) {
            System.out.println("Paiement refusé : le montant dépasse le solde restant.");
        } else {
            System.out.println("Paiement enregistré : " + paiement.toChaine());
            if (facture.estSoldee()) {
                System.out.println("La facture est maintenant soldée. La commande est payée.");
            }
        }
    }

    private void afficherPaiementsDeFacture() {
        int id = Saisie.lireEntier(scanner, "Id de la facture : ");
        Facture facture = factureService.trouverParId(id);
        if (facture == null) {
            System.out.println("Facture introuvable.");
            return;
        }
        List<Paiement> paiements = paiementService.listerPaiementsDeFacture(facture);
        if (paiements.isEmpty()) {
            System.out.println("Aucun paiement enregistré pour cette facture.");
            return;
        }
        for (Paiement paiement : paiements) {
            System.out.println(paiement.toChaine());
        }
    }
}
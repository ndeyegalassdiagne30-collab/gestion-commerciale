package src.view;

import src.entities.Client;
import src.entities.Commande;
import src.entities.Produit;
import src.service.ClientService;
import src.service.CommandeService;
import src.service.ProduitService;
import java.util.List;
import java.util.Scanner;

/**
 * Gère les interactions console pour la gestion des commandes.
 */
public class CommandeView {

    private CommandeService commandeService;
    private ClientService clientService;
    private ProduitService produitService;
    private Scanner scanner;

    public CommandeView(CommandeService commandeService, ClientService clientService,
            ProduitService produitService, Scanner scanner) {
        this.commandeService = commandeService;
        this.clientService = clientService;
        this.produitService = produitService;
        this.scanner = scanner;
    }

    public void afficherMenu() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n--- GESTION DES COMMANDES ---");
            System.out.println("1. Créer une commande");
            System.out.println("2. Afficher les produits d'une commande");
            System.out.println("3. Afficher la liste des commandes");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    creerCommande();
                    break;
                case "2":
                    afficherProduitsDeCommande();
                    break;
                case "3":
                    afficherCommandes();
                    break;
                case "0":
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void creerCommande() {
        int idClient = Saisie.lireEntier(scanner, "Id du client : ");
        Client client = clientService.trouverParId(idClient);
        if (client == null) {
            System.out.println("Client introuvable.");
            return;
        }

        System.out.print("Numéro de la commande : ");
        String numero = scanner.nextLine();
        Commande commande = commandeService.creerCommande(numero, client);

        // Une commande doit contenir au moins un produit
        boolean ajouterUnAutre = true;
        while (ajouterUnAutre) {
            int idProduit = Saisie.lireEntier(scanner, "Id du produit à ajouter : ");
            Produit produit = produitService.trouverParId(idProduit);
            if (produit == null) {
                System.out.println("Produit introuvable.");
            } else {
                int quantite = Saisie.lireEntierStrictementPositif(scanner, "Quantité : ");
                boolean ok = commandeService.ajouterProduitACommande(commande, produit, quantite);
                if (!ok) {
                    System.out.println("Stock insuffisant pour ce produit.");
                } else {
                    System.out.println("Produit ajouté à la commande.");
                }
            }

            System.out.print("Ajouter un autre produit ? (o/n) : ");
            ajouterUnAutre = scanner.nextLine().equalsIgnoreCase("o");
        }

        boolean validee = commandeService.validerCommande(commande);
        if (validee) {
            System.out.println("Commande validée : " + commande.toChaine());
        } else {
            System.out.println("La commande n'a pas pu être validée (aucun produit).");
        }
    }

    private void afficherProduitsDeCommande() {
        int id = Saisie.lireEntier(scanner, "Id de la commande : ");
        Commande commande = commandeService.trouverParId(id);
        if (commande == null) {
            System.out.println("Commande introuvable.");
            return;
        }
        commande.getLignes().forEach(ligne -> System.out.println(ligne.toChaine()));
    }

    private void afficherCommandes() {
        List<Commande> commandes = commandeService.listerCommandes();
        if (commandes.isEmpty()) {
            System.out.println("Aucune commande enregistrée.");
            return;
        }
        for (Commande commande : commandes) {
            System.out.println(commande.toChaine());
        }
    }
}
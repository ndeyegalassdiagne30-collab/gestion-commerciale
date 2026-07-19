import repository.ClientRepository;
import repository.CommandeRepository;
import repository.FactureRepository;
import repository.PaiementRepository;
import repository.ProduitRepository;
import service.ClientService;
import service.CommandeService;
import service.FactureService;
import service.PaiementService;
import service.ProduitService;
import src.view.ClientView;
import src.view.CommandeView;
import src.view.FactureView;
import src.view.MenuView;
import src.view.PaiementView;
import src.view.ProduitView;

import java.util.Scanner;

/**
 * Point d'entrée de l'application.
 * Construit les repositories, les services, les vues, puis lance le menu principal.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Couche repository : stockage en mémoire
        ClientRepository clientRepository = new ClientRepository();
        ProduitRepository produitRepository = new ProduitRepository();
        CommandeRepository commandeRepository = new CommandeRepository();
        FactureRepository factureRepository = new FactureRepository();
        PaiementRepository paiementRepository = new PaiementRepository();

        // Couche service : logique métier
        ClientService clientService = new ClientService(clientRepository);
        ProduitService produitService = new ProduitService(produitRepository);
        CommandeService commandeService = new CommandeService(commandeRepository);
        FactureService factureService = new FactureService(factureRepository);
        PaiementService paiementService = new PaiementService(paiementRepository);

        // Couche view : interactions console
        ClientView clientView = new ClientView(clientService, scanner);
        ProduitView produitView = new ProduitView(produitService, scanner);
        CommandeView commandeView = new CommandeView(commandeService, clientService, produitService, scanner);
        FactureView factureView = new FactureView(factureService, commandeService, scanner);
        PaiementView paiementView = new PaiementView(paiementService, factureService, scanner);

        MenuView menuView = new MenuView(clientView, produitView, commandeView, factureView, paiementView, scanner);
        menuView.demarrer();

        scanner.close();
    }
}
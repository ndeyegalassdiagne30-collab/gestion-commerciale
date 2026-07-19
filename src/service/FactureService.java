package service;

import entities.Commande;
import entities.Facture;
import repository.FactureRepository;
import java.util.List;

/**
 * Contient la logique métier liée aux factures.
 */
public class FactureService {

    private FactureRepository factureRepository = new FactureRepository();

    public Facture genererFacture(Commande commande, String numero) {
        if (!commande.isValidee()) {
            return null;
        }
        Facture facture = new Facture(numero, commande);
        return factureRepository.ajouter(facture);
    }

    public List<Facture> listerFactures() {
        return factureRepository.getTous();
    }

    public Facture trouverParId(int id) {
        return factureRepository.trouverParId(id);
    }

    public Facture trouverParCommande(Commande commande) {
        return factureRepository.trouverParCommande(commande);
    }

    // Bonus : factures impayées ou partiellement payées
    public List<Facture> listerFacturesImpayeesOuPartielles() {
        List<Facture> resultat = new java.util.ArrayList<>();
        for (Facture facture : listerFactures()) {
            if (!facture.estSoldee()) {
                resultat.add(facture);
            }
        }
        return resultat;
    }
}

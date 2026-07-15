package src.service;

import src.entities.Commande;
import src.entities.Facture;
import src.repository.FactureRepository;
import java.util.List;

/**
 * Contient la logique métier liée aux factures.
 * Règle de gestion : une facture est générée automatiquement après
 * la validation d'une commande, et une facture est liée à une seule commande.
 */
public class FactureService {

    private FactureRepository factureRepository;

    public FactureService(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    /**
     * Génère une facture pour une commande déjà validée.
     * Retourne null si la commande n'est pas validée.
     */
    public Facture genererFacture(Commande commande, String numeroFacture) {
        if (!commande.isValidee()) {
            return null;
        }
        return factureRepository.ajouter(numeroFacture, commande);
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

    // Bonus : liste des factures impayées ou partiellement payées
    public List<Facture> listerFacturesImpayeesOuPartielles() {
        return factureRepository.trouverFacturesImpayeesOuPartielles();
    }
}
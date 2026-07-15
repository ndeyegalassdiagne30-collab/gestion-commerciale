package src.service;

import src.entities.Facture;
import src.entities.Paiement;
import src.repository.PaiementRepository;
import java.util.List;

/**
 * Contient la logique métier liée aux paiements.
 * Règle de gestion : le montant total des paiements ne doit jamais
 * dépasser le montant de la facture.
 */
public class PaiementService {

    private PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    /**
     * Enregistre un paiement pour une facture, si le montant ne dépasse pas
     * le montant restant à payer. Retourne null si le paiement est refusé.
     */
    public Paiement enregistrerPaiement(Facture facture, String numero, double montant) {
        if (montant <= 0 || montant > facture.getMontantRestant()) {
            return null;
        }
        Paiement paiement = paiementRepository.ajouter(numero, montant, facture);
        facture.ajouterPaiement(paiement);
        return paiement;
    }

    public List<Paiement> listerPaiementsDeFacture(Facture facture) {
        return paiementRepository.trouverParFacture(facture);
    }
}
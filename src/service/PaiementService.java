package service;

import entities.Facture;
import entities.Paiement;
import repository.PaiementRepository;

/**
 * Contient la logique métier liée aux paiements.
 * Règle de gestion : le total des paiements ne doit jamais dépasser le montant de la facture.
 */
public class PaiementService {

    private PaiementRepository paiementRepository = new PaiementRepository();

    public Paiement enregistrerPaiement(Facture facture, String numero, double montant) {
        if (montant <= 0 || montant > facture.getMontantRestant()) {
            return null;
        }
        Paiement paiement = new Paiement(numero, montant, facture);
        paiementRepository.ajouter(paiement);
        facture.ajouterPaiement(paiement);
        return paiement;
    }
}

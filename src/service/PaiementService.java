package service;

import entities.Facture;
import entities.Paiement;
import repository.PaiementRepository;

public class PaiementService {

    private PaiementRepository paiementRepository = new PaiementRepository();

    /**
     * Enregistre un paiement, sauf si :
     * - le montant est invalide ou dépasse le solde restant
     * - le numéro de paiement existe déjà (doublon)
     */
    public Paiement enregistrerPaiement(Facture facture, String numero, double montant) {
        if (montant <= 0 || montant > facture.getMontantRestant()) {
            return null;
        }
        if (paiementRepository.existeParNumero(numero)) {
            return null;
        }
        Paiement paiement = new Paiement(numero, montant, facture);
        paiementRepository.ajouter(paiement);
        facture.ajouterPaiement(paiement);
        return paiement;
    }
}
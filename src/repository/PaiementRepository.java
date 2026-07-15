package src.repository;

import src.entities.Facture;
import src.entities.Paiement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère le stockage et la récupération des paiements en mémoire.
 */
public class PaiementRepository {

    private List<Paiement> paiements = new ArrayList<>();
    private int prochainId = 1;

    public Paiement ajouter(String numero, double montantVerse, Facture facture) {
        Paiement paiement = new Paiement(prochainId, numero, LocalDate.now(), montantVerse, facture);
        paiements.add(paiement);
        prochainId++;
        return paiement;
    }

    public List<Paiement> getTous() {
        return paiements;
    }

    /**
     * Retourne tous les paiements liés à une facture donnée.
     */
    public List<Paiement> trouverParFacture(Facture facture) {
        List<Paiement> resultat = new ArrayList<>();
        for (Paiement paiement : paiements) {
            if (paiement.getFacture().getId() == facture.getId()) {
                resultat.add(paiement);
            }
        }
        return resultat;
    }
}
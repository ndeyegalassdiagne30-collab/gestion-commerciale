package src.repository;

import src.entities.Commande;
import src.entities.Facture;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère le stockage et la récupération des factures en mémoire.
 */
public class FactureRepository {

    private List<Facture> factures = new ArrayList<>();
    private int prochainId = 1;

    public Facture ajouter(String numero, Commande commande) {
        Facture facture = new Facture(prochainId, numero, LocalDate.now(), commande);
        factures.add(facture);
        prochainId++;
        return facture;
    }

    public List<Facture> getTous() {
        return factures;
    }

    public Facture trouverParId(int id) {
        for (Facture facture : factures) {
            if (facture.getId() == id) {
                return facture;
            }
        }
        return null;
    }

    /**
     * Retrouve la facture associée à une commande précise.
     */
    public Facture trouverParCommande(Commande commande) {
        for (Facture facture : factures) {
            if (facture.getCommande().getId() == commande.getId()) {
                return facture;
            }
        }
        return null;
    }

    /**
     * Bonus : retourne toutes les factures non soldées ou partiellement payées.
     */
    public List<Facture> trouverFacturesImpayeesOuPartielles() {
        List<Facture> resultat = new ArrayList<>();
        for (Facture facture : factures) {
            if (!facture.estSoldee()) {
                resultat.add(facture);
            }
        }
        return resultat;
    }
}
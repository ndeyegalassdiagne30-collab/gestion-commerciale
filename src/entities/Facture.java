package src.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente une facture générée automatiquement après la validation d'une
 * commande.
 * Une facture est liée à une seule commande et peut recevoir plusieurs
 * paiements.
 */
public class Facture {

    private int id;
    private String numero;
    private LocalDate date;
    private double montant;
    private Commande commande;
    private List<Paiement> paiements;

    public Facture(int id, String numero, LocalDate date, Commande commande) {
        this.id = id;
        this.numero = numero;
        this.date = date;
        this.commande = commande;
        // Le montant de la facture correspond au montant total de la commande
        this.montant = commande.getMontantTotal();
        this.paiements = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }

    public Commande getCommande() {
        return commande;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    /**
     * Ajoute un paiement à la facture.
     * Cette méthode ne vérifie pas les règles de gestion : c'est le rôle du
     * service.
     */
    public void ajouterPaiement(Paiement paiement) {
        paiements.add(paiement);
    }

    /**
     * Calcule le montant total déjà versé pour cette facture.
     */
    public double getMontantVerse() {
        double total = 0;
        for (Paiement paiement : paiements) {
            total += paiement.getMontantVerse();
        }
        return total;
    }

    /**
     * Calcule le montant restant à payer.
     */
    public double getMontantRestant() {
        return montant - getMontantVerse();
    }

    /**
     * Une facture est soldée quand la somme des paiements égale son montant.
     */
    public boolean estSoldee() {
        return getMontantVerse() >= montant;
    }

    public String toChaine() {
        return "Facture #" + numero + " du " + date + " - Montant: " + montant
                + " FCFA - Restant: " + getMontantRestant() + " FCFA"
                + (estSoldee() ? " [SOLDEE]" : " [NON SOLDEE]");
    }
}
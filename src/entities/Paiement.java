package src.entities;

import java.time.LocalDate;

/**
 * Représente un paiement effectué par un client pour régler une facture.
 * Une facture peut recevoir plusieurs paiements (règlement en plusieurs fois).
 */
public class Paiement {

    private int id;
    private String numero;
    private LocalDate date;
    private double montantVerse;
    private Facture facture;

    public Paiement(int id, String numero, LocalDate date, double montantVerse, Facture facture) {
        this.id = id;
        this.numero = numero;
        this.date = date;
        this.montantVerse = montantVerse;
        this.facture = facture;
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

    public double getMontantVerse() {
        return montantVerse;
    }

    public Facture getFacture() {
        return facture;
    }

    public String toChaine() {
        return "Paiement #" + numero + " du " + date + " - Montant versé: " + montantVerse + " FCFA";
    }
}
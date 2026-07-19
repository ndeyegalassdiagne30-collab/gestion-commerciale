package src.entities;

import java.time.LocalDate;

/**
 * Représente un paiement effectué pour régler une facture.
 */
public class Paiement {

    private int id;
    private String numero;
    private LocalDate date;
    private double montantVerse;
    private Facture facture;

    public Paiement(String numero, double montantVerse, Facture facture) {
        this.numero = numero;
        this.date = LocalDate.now();
        this.montantVerse = montantVerse;
        this.facture = facture;
    }

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

    public void setId(int id) {
        this.id = id;
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
        return "Paiement :" + "\n"
                + "  Id      : " + id + "\n"
                + "  Numéro  : " + numero + "\n"
                + "  Date    : " + date + "\n"
                + "  Montant : " + montantVerse + " FCFA";
    }
}
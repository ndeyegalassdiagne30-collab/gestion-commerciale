package src.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente une facture générée après validation d'une commande.
 * Peut recevoir plusieurs paiements.
 */
public class Facture {

    private int id;
    private String numero;
    private LocalDate date;
    private double montant;
    private Commande commande;
    private List<Paiement> paiements;

    public Facture(String numero, Commande commande) {
        this.numero = numero;
        this.date = LocalDate.now();
        this.commande = commande;
        this.montant = commande.getMontantTotal();
        this.paiements = new ArrayList<>();
    }

    public Facture(int id, String numero, LocalDate date, double montant, Commande commande) {
        this.id = id;
        this.numero = numero;
        this.date = date;
        this.montant = montant;
        this.commande = commande;
        this.paiements = new ArrayList<>();
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

    public double getMontant() {
        return montant;
    }

    public Commande getCommande() {
        return commande;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void ajouterPaiement(Paiement paiement) {
        paiements.add(paiement);
    }

    public double getMontantVerse() {
        double total = 0;
        for (Paiement paiement : paiements) {
            total += paiement.getMontantVerse();
        }
        return total;
    }

    public double getMontantRestant() {
        return montant - getMontantVerse();
    }

    public boolean estSoldee() {
        return getMontantVerse() >= montant;
    }

    public String toChaine() {
        return "Facture :" + "\n"
                + "  Id      : " + id + "\n"
                + "  Numéro  : " + numero + "\n"
                + "  Date    : " + date + "\n"
                + "  Montant : " + montant + " FCFA\n"
                + "  Restant : " + getMontantRestant() + " FCFA\n"
                + "  Statut  : " + (estSoldee() ? "SOLDEE" : "NON SOLDEE");
    }
}
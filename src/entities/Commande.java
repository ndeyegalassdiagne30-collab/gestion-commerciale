package src.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente une commande passée par un client.
 * Une commande appartient à un seul client et contient une ou plusieurs lignes
 * de commande.
 */
public class Commande {

    private int id;
    private String numero;
    private LocalDate date;
    private double montantTotal;
    private Client client;
    private List<LigneCommande> lignes;
    private boolean validee;

    public Commande(int id, String numero, LocalDate date, Client client) {
        this.id = id;
        this.numero = numero;
        this.date = date;
        this.client = client;
        this.lignes = new ArrayList<>();
        this.montantTotal = 0;
        this.validee = false;
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

    public double getMontantTotal() {
        return montantTotal;
    }

    public Client getClient() {
        return client;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }

    public boolean isValidee() {
        return validee;
    }

    public void setValidee(boolean validee) {
        this.validee = validee;
    }

    /**
     * Ajoute une ligne de commande (un produit + une quantité) à la commande.
     */
    public void ajouterLigne(LigneCommande ligne) {
        lignes.add(ligne);
        recalculerMontantTotal();
    }

    /**
     * Recalcule le montant total en additionnant le sous-total de chaque ligne.
     */
    private void recalculerMontantTotal() {
        double total = 0;
        for (LigneCommande ligne : lignes) {
            total += ligne.getSousTotal();
        }
        this.montantTotal = total;
    }

    public String toChaine() {
        return "----------------Commande-----------------" + "\n"
            + "Numéro :" + numero + "\n"
            + "Date : "+ date + "\n"
            + "Client :"+ client.getPrenom() + " "+client.getNom()+"\n"
            + "Total :" + montantTotal + " FCFA"+ "\n"
            + "Statut : "+ (validee ? " [VALIDEE]" : " [NON VALIDEE]") + "\n"
            + "-------------------------------------------------";
    }
}
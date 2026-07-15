package src.entities;

/**
 * Représente un produit vendu par le magasin.
 * Chaque produit a un stock et un prix unitaire.
 */
public class Produit {

    private int id;
    private String libelle;
    private int quantiteEnStock;
    private double prixUnitaire;

    public Produit(int id, String libelle, int quantiteEnStock, double prixUnitaire) {
        this.id = id;
        this.libelle = libelle;
        this.quantiteEnStock = quantiteEnStock;
        this.prixUnitaire = prixUnitaire;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    /**
     * Diminue le stock après une vente.
     * Retourne false si le stock est insuffisant.
     */
    public boolean retirerDuStock(int quantite) {
        if (quantite > this.quantiteEnStock) {
            return false;
        }
        this.quantiteEnStock -= quantite;
        return true;
    }

    public String toChaine() {
        return "Produit #" + id + " - " + libelle + " (stock: " + quantiteEnStock
                + ", prix unitaire: " + prixUnitaire + " FCFA)";
    }
}
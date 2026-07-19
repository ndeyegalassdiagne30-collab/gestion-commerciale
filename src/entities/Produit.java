package src.entities;

/**
 * Représente un produit vendu par le magasin.
 */
public class Produit {

    private int id;
    private String libelle;
    private int quantiteEnStock;
    private double prixUnitaire;

    public Produit(String libelle, int quantiteEnStock, double prixUnitaire) {
        this.libelle = libelle;
        this.quantiteEnStock = quantiteEnStock;
        this.prixUnitaire = prixUnitaire;
    }

    public Produit(int id, String libelle, int quantiteEnStock, double prixUnitaire) {
        this.id = id;
        this.libelle = libelle;
        this.quantiteEnStock = quantiteEnStock;
        this.prixUnitaire = prixUnitaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
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

    public String toChaine() {
        return "Produit :" + "\n"
                + "  Id       : " + id + "\n"
                + "  Libellé  : " + libelle + "\n"
                + "  Stock    : " + quantiteEnStock + "\n"
                + "  Prix     : " + prixUnitaire + " FCFA";
    }
}
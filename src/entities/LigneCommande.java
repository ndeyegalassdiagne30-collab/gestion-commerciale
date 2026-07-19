package src.entities;

/**
 * Une ligne de commande associe un produit à une quantité commandée.
 * Une commande est composée d'une ou plusieurs lignes de commande.
 */
public class LigneCommande {

    private Produit produit;
    private int quantite;

    public LigneCommande(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getQuantite() {
        return quantite;
    }

    /**
     * Calcule le sous-total de la ligne : prix unitaire * quantité.
     */
    public double getSousTotal() {
        return produit.getPrixUnitaire() * quantite;
    }

    public String toChaine() {
    return "----------------Ligne Commande-----------------" + "\n"
        + "Produit : " + produit.getLibelle() + "\n"
        + "Quantité : " + quantite + "\n"
        + "Sous-total : " + getSousTotal() + " FCFA" + "\n"
        + "-------------------------------------------------";
}
}
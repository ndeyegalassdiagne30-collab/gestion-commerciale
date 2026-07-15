package src.entities;

/**
 * Représente un client du magasin.
 * Un client peut passer plusieurs commandes.
 */
public class Client {

    private int id;
    private String nom;
    private String prenom;
    private String telephone;

    public Client(int id, String nom, String prenom, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String toChaine() {
        return "Client #" + id + " - " + prenom + " " + nom + " (tel: " + telephone + ")";
    }
}
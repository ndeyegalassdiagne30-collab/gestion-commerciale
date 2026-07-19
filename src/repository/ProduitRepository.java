
package repository;

import src.config.ConnexionBD;
import src.entities.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Réalise les opérations SQL liées à la table "produit".
 */
public class ProduitRepository {

    private Connection connection = ConnexionBD.getInstance().getConnection();

    public Produit ajouter(Produit produit) {
        String sql = "INSERT INTO produit (libelle, quantite_stock, prix_unitaire) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, produit.getLibelle());
            ps.setInt(2, produit.getQuantiteEnStock());
            ps.setDouble(3, produit.getPrixUnitaire());
            ps.executeUpdate();

            try (ResultSet cles = ps.getGeneratedKeys()) {
                if (cles.next()) {
                    produit.setId(cles.getInt(1));
                }
            }
            return produit;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du produit", e);
        }
    }

    public List<Produit> getTous() {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                produits.add(mapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des produits", e);
        }
        return produits;
    }

    public Produit trouverParId(int id) {
        String sql = "SELECT * FROM produit WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche du produit", e);
        }
        return null;
    }

    public List<Produit> rechercherParLibelle(String libelle) {
        List<Produit> resultat = new ArrayList<>();
        String sql = "SELECT * FROM produit WHERE libelle LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + libelle + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultat.add(mapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche par libellé", e);
        }
        return resultat;
    }

    /**
     * Met à jour le stock d'un produit (utilisé après un retrait de stock).
     */
    public void mettreAJourStock(Produit produit) {
        String sql = "UPDATE produit SET quantite_stock = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, produit.getQuantiteEnStock());
            ps.setInt(2, produit.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour du stock", e);
        }
    }

    private Produit mapper(ResultSet rs) throws SQLException {
        return new Produit(
                rs.getInt("id"),
                rs.getString("libelle"),
                rs.getInt("quantite_stock"),
                rs.getDouble("prix_unitaire")
        );
    }
}
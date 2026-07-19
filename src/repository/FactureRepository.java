package repository;

import config.ConnexionBD;
import entities.Commande;
import entities.Facture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Réalise les opérations SQL liées à la table "facture".
 */
public class FactureRepository {

    private Connection connection = ConnexionBD.getInstance().getConnection();
    private CommandeRepository commandeRepository = new CommandeRepository();

    public Facture ajouter(Facture facture) {
        String sql = "INSERT INTO facture (numero, date_facture, montant, commande_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, facture.getNumero());
            ps.setDate(2, Date.valueOf(facture.getDate()));
            ps.setDouble(3, facture.getMontant());
            ps.setInt(4, facture.getCommande().getId());
            ps.executeUpdate();

            try (ResultSet cles = ps.getGeneratedKeys()) {
                if (cles.next()) {
                    facture.setId(cles.getInt(1));
                }
            }
            return facture;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de la facture", e);
        }
    }

    public List<Facture> getTous() {
        List<Facture> factures = new ArrayList<>();
        String sql = "SELECT * FROM facture";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                factures.add(mapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des factures", e);
        }
        return factures;
    }

    public Facture trouverParId(int id) {
        String sql = "SELECT * FROM facture WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche de la facture", e);
        }
        return null;
    }

    public Facture trouverParCommande(Commande commande) {
        String sql = "SELECT * FROM facture WHERE commande_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, commande.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche de la facture par commande", e);
        }
        return null;
    }

    private Facture mapper(ResultSet rs) throws SQLException {
        Commande commande = commandeRepository.trouverParId(rs.getInt("commande_id"));
        Facture facture = new Facture(
                rs.getInt("id"),
                rs.getString("numero"),
                rs.getDate("date_facture").toLocalDate(),
                rs.getDouble("montant"),
                commande
        );
        facture.getPaiements().addAll(new PaiementRepository().trouverParFacture(facture));
        return facture;
    }
}

repository/PaiementRepository.java

package repository;

import config.ConnexionBD;
import entities.Facture;
import entities.Paiement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Réalise les opérations SQL liées à la table "paiement".
 */
public class PaiementRepository {

    private Connection connection = ConnexionBD.getInstance().getConnection();

    public Paiement ajouter(Paiement paiement) {
        String sql = "INSERT INTO paiement (numero, date_paiement, montant_verse, facture_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, paiement.getNumero());
            ps.setDate(2, Date.valueOf(paiement.getDate()));
            ps.setDouble(3, paiement.getMontantVerse());
            ps.setInt(4, paiement.getFacture().getId());
            ps.executeUpdate();

            try (ResultSet cles = ps.getGeneratedKeys()) {
                if (cles.next()) {
                    paiement.setId(cles.getInt(1));
                }
            }
            return paiement;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du paiement", e);
        }
    }

    /**
     * Récupère les paiements d'une facture, sans réappeler FactureRepository
     * (pour éviter une boucle infinie de mapping entre les deux repositories).
     */
    public List<Paiement> trouverParFacture(Facture facture) {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement WHERE facture_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, facture.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    paiements.add(new Paiement(
                            rs.getInt("id"),
                            rs.getString("numero"),
                            rs.getDate("date_paiement").toLocalDate(),
                            rs.getDouble("montant_verse"),
                            facture
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des paiements", e);
        }
        return paiements;
    }
}
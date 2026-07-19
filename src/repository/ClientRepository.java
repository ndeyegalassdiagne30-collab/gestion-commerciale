package repository;

import config.ConnexionBD;
import entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Réalise les opérations SQL liées à la table "client".
 * Utilise la connexion unique fournie par le Singleton ConnexionBD.
 */
public class ClientRepository {

    private Connection connection = ConnexionBD.getInstance().getConnection();

    /**
     * Insère un nouveau client et renvoie l'id généré par la base de données.
     */
    public Client ajouter(Client client) {
        String sql = "INSERT INTO client (nom, prenom, telephone) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getTelephone());
            ps.executeUpdate();

            // Récupère l'id auto-généré par MySQL et le fixe sur l'objet
            try (ResultSet cles = ps.getGeneratedKeys()) {
                if (cles.next()) {
                    client.setId(cles.getInt(1));
                }
            }
            return client;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du client", e);
        }
    }

    public List<Client> getTous() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clients.add(mapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des clients", e);
        }
        return clients;
    }

    public Client trouverParId(int id) {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche du client", e);
        }
        return null;
    }

    // Bonus : recherche par téléphone (LIKE pour une recherche partielle)
    public List<Client> rechercherParTelephone(String telephone) {
        List<Client> resultat = new ArrayList<>();
        String sql = "SELECT * FROM client WHERE telephone LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + telephone + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultat.add(mapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche par téléphone", e);
        }
        return resultat;
    }

    /**
     * Transforme une ligne du ResultSet en objet Client.
     */
    private Client mapper(ResultSet rs) throws SQLException {
        return new Client(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("telephone")
        );
    }
}
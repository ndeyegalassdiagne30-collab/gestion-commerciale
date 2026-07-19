

package repository;

import src.config.ConnexionBD;
import src.entities.Client;
import src.entities.Commande;
import src.entities.LigneCommande;
import src.entities.Produit;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Réalise les opérations SQL liées aux tables "commande" et "ligne_commande".
 */
public class CommandeRepository {

    private Connection connection = ConnexionBD.getInstance().getConnection();
    private ProduitRepository produitRepository = new ProduitRepository();
    private ClientRepository clientRepository = new ClientRepository();

    public Commande ajouter(Commande commande) {
        String sql = "INSERT INTO commande (numero, date_commande, montant_total, validee, client_id) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, commande.getNumero());
            ps.setDate(2, Date.valueOf(commande.getDate()));
            ps.setDouble(3, commande.getMontantTotal());
            ps.setBoolean(4, commande.isValidee());
            ps.setInt(5, commande.getClient().getId());
            ps.executeUpdate();

            try (ResultSet cles = ps.getGeneratedKeys()) {
                if (cles.next()) {
                    commande.setId(cles.getInt(1));
                }
            }
            return commande;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de la commande", e);
        }
    }

    /**
     * Ajoute une ligne de commande en base et met à jour le montant total de la commande.
     */
    public void ajouterLigne(Commande commande, LigneCommande ligne) {
        String sql = "INSERT INTO ligne_commande (commande_id, produit_id, quantite) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, commande.getId());
            ps.setInt(2, ligne.getProduit().getId());
            ps.setInt(3, ligne.getQuantite());
            ps.executeUpdate();

            mettreAJourMontantTotal(commande);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de la ligne de commande", e);
        }
    }

    private void mettreAJourMontantTotal(Commande commande) {
        String sql = "UPDATE commande SET montant_total = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, commande.getMontantTotal());
            ps.setInt(2, commande.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour du montant total", e);
        }
    }

    public void valider(Commande commande) {
        String sql = "UPDATE commande SET validee = TRUE WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, commande.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la validation de la commande", e);
        }
    }

    public List<Commande> getTous() {
        List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commande";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                commandes.add(mapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des commandes", e);
        }
        return commandes;
    }

    public Commande trouverParId(int id) {
        String sql = "SELECT * FROM commande WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Commande commande = mapper(rs);
                    commande.getLignes().addAll(getLignesDeCommande(commande));
                    return commande;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche de la commande", e);
        }
        return null;
    }

    /**
     * Récupère toutes les lignes (produits + quantités) d'une commande donnée.
     */
    public List<LigneCommande> getLignesDeCommande(Commande commande) {
        List<LigneCommande> lignes = new ArrayList<>();
        String sql = "SELECT * FROM ligne_commande WHERE commande_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, commande.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Produit produit = produitRepository.trouverParId(rs.getInt("produit_id"));
                    lignes.add(new LigneCommande(rs.getInt("id"), produit, rs.getInt("quantite")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des lignes de commande", e);
        }
        return lignes;
    }

    private Commande mapper(ResultSet rs) throws SQLException {
        Client client = clientRepository.trouverParId(rs.getInt("client_id"));
        return new Commande(
                rs.getInt("id"),
                rs.getString("numero"),
                rs.getDate("date_commande").toLocalDate(),
                rs.getDouble("montant_total"),
                rs.getBoolean("validee"),
                client
        );
    }
}
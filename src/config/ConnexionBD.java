package src.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gère la connexion unique à la base de données (patron de conception Singleton).
 * Une seule instance de Connection est créée pour toute l'application,
 * ce qui évite d'ouvrir une nouvelle connexion à chaque requête SQL.
 */
public class ConnexionBD {

    // Paramètres de connexion à adapter selon ton environnement
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_commerciale";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    // Unique instance de la classe (Singleton)
    private static ConnexionBD instance;

    // Unique connexion partagée par toute l'application
    private Connection connection;

    /**
     * Constructeur privé : personne ne peut créer d'instance depuis l'extérieur.
     * C'est le cœur du patron Singleton.
     */
    private ConnexionBD() {
        try {
            connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
        } catch (SQLException e) {
            throw new RuntimeException("Impossible de se connecter à la base de données", e);
        }
    }

    /**
     * Point d'accès unique à l'instance.
     * Si l'instance n'existe pas encore, elle est créée une seule fois.
     */
    public static ConnexionBD getInstance() {
        if (instance == null) {
            instance = new ConnexionBD();
        }
        return instance;
    }

    /**
     * Retourne la connexion active, utilisée par tous les repositories.
     */
    public Connection getConnection() {
        return connection;
    }
}
package eu.tinky.cfaction.manager;

import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;
    private final String host;
    private final int port;
    private final String databaseName;
    private final String username;
    private final String password;

    public DatabaseManager(FileConfiguration config) {
        this.host = config.getString("database.host");
        this.port = config.getInt("database.port");
        this.databaseName = config.getString("database.name");
        this.username = config.getString("database.username");
        this.password = config.getString("database.password");
    }

    public void connect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?useSSL=false&autoReconnect=true&characterEncoding=utf8";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("[DatabaseManager] Connexion établie avec succès !");
        } catch (SQLException e) {
            System.err.println("[DatabaseManager] Erreur lors de la connexion à la base de données : " + e.getMessage());
            throw e;
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connect();
        }
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("[DatabaseManager] Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}

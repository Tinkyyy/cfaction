package eu.tinky.cfaction.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {
    private final Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public int getOrCreatePlayer(String uuid, String username) throws SQLException {
        String selectSql = "SELECT id, username FROM players WHERE uuid = ?";
        String insertSql = "INSERT INTO players (uuid, username) VALUES (?, ?)";
        String updateSql = "UPDATE players SET username = ? WHERE uuid = ?";

        try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
            selectStmt.setString(1, uuid);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    int playerId = rs.getInt("id");
                    String storedUsername = rs.getString("username");

                    // Étape 2 : Mettre à jour le nom si nécessaire
                    if (!storedUsername.equals(username)) {
                        try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                            updateStmt.setString(1, username);
                            updateStmt.setString(2, uuid);
                            updateStmt.executeUpdate();
                        }
                    }

                    return playerId;
                }
            }
        }

        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStmt.setString(1, uuid);
            insertStmt.setString(2, username);
            insertStmt.executeUpdate();

            try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }

        throw new SQLException("Impossible d'insérer ou de récupérer le joueur.");
    }
}

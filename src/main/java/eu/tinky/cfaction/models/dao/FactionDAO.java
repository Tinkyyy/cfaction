package eu.tinky.cfaction.models.dao;

import eu.tinky.cfaction.models.Faction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FactionDAO {
    private final Connection connection;

    public FactionDAO(Connection connection) {
        this.connection = connection;
    }

    public Faction getFactionByPlayerUUID(String playerUUID) {
        String sql = "SELECT f.id, f.tag, f.description, f.leader_id, f.dtr, f.home_x, f.home_y, f.home_z, " +
                "f.claim_x_min, f.claim_x_max, f.claim_y_min, f.claim_y_max, f.claim_z_min, f.claim_z_max " +
                "FROM factions f " +
                "LEFT JOIN faction_members fm ON f.id = fm.faction_id " +
                "LEFT JOIN players p ON (fm.player_id = p.id OR f.leader_id = p.id) " +
                "WHERE p.uuid = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, playerUUID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Faction(
                            rs.getInt("id"),
                            rs.getString("tag"),
                            rs.getString("description"),
                            rs.getInt("leader_id"),
                            rs.getDouble("dtr"),
                            rs.getFloat("home_x"),
                            rs.getFloat("home_y"),
                            rs.getFloat("home_z"),
                            rs.getInt("claim_x_min"),
                            rs.getInt("claim_x_max"),
                            rs.getInt("claim_y_min"),
                            rs.getInt("claim_y_max"),
                            rs.getInt("claim_z_min"),
                            rs.getInt("claim_z_max")
                    );
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // TODO: Manage this catch
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // TODO: Manage this catch
        }
        return null;
    }

    public void createFaction(Faction faction) {
        String sql = "INSERT INTO factions (tag, description, leader_id, dtr, home_x, home_y, home_z, claim_x_min, claim_x_max, claim_y_min, claim_y_max, claim_z_min, claim_z_max) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, faction.getTag());
            stmt.setString(2, faction.getDescription());
            stmt.setInt(3, faction.getLeaderId());
            stmt.setDouble(4, faction.getDtr());
            stmt.setFloat(5, faction.getHomeX());
            stmt.setFloat(6, faction.getHomeY());
            stmt.setFloat(7, faction.getHomeZ());
            stmt.setInt(8, faction.getClaimXMin());
            stmt.setInt(9, faction.getClaimXMax());
            stmt.setInt(10, faction.getClaimYMin());
            stmt.setInt(11, faction.getClaimYMax());
            stmt.setInt(12, faction.getClaimZMin());
            stmt.setInt(13, faction.getClaimZMax());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // TODO: Manage this catch
        }
    }
}

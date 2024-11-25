package eu.tinky.cfaction;

import eu.tinky.cfaction.manager.DatabaseManager;
import eu.tinky.cfaction.manager.GlobalManager;
import eu.tinky.cfaction.manager.LangManager;
import eu.tinky.cfaction.models.dao.FactionDAO;
import eu.tinky.cfaction.models.dao.PlayerDAO;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class CFaction extends JavaPlugin {

    private static CFaction instance;
    private DatabaseManager databaseManager;
    private PlayerDAO playerDAO;
    private FactionDAO factionDAO;
    private LangManager langManager;
    private GlobalManager globalManager;

    @Override
    public void onLoad() {
        this.saveDefaultConfig();
        CFactionLoader.copyResourcesFoldersIfNotExists(this);
    }

    @Override
    public void onEnable() {
        instance = this;

        this.databaseManager = new DatabaseManager(this.getConfig());

        try {
            this.databaseManager.connect();
            this.playerDAO = new PlayerDAO(this.databaseManager.getConnection());
            this.factionDAO = new FactionDAO(this.databaseManager.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.langManager = CFactionLoader.loadLangManager(this);
        this.globalManager = new GlobalManager();

        CFactionLoader.registerCommands(this);
        CFactionLoader.registerListeners(this);
    }

    @Override
    public void onDisable() {

    }

    public static CFaction getInstance() {
        return instance;
    }

    public LangManager getLangManager(){
        return this.langManager;
    }

    public GlobalManager getGlobalManager() {
        return this.globalManager;
    }

    public DatabaseManager getDatabaseManager() {
        return this.databaseManager;
    }

    public PlayerDAO getPlayerDAO() {
        return this.playerDAO;
    }

    public FactionDAO getFactionDAO() {
        return this.factionDAO;
    }
}

package eu.tinky.cfaction;

import eu.tinky.cfaction.command.faction.FactionCommand;
import eu.tinky.cfaction.listener.FactionListener;
import eu.tinky.cfaction.listener.PlayerListener;
import eu.tinky.cfaction.manager.LangManager;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;

public class CFactionLoader {

    protected static LangManager loadLangManager(CFaction plugin){
        try {
            return new LangManager();
        } catch(InvalidConfigurationException exception) {
            plugin.getPluginLoader().disablePlugin(plugin);
        }
        plugin.getLogger().info("LangManager loaded successfully !");
        return null;
    }

    protected static void registerCommands(CFaction plugin) {
        FactionCommand factionCommand = new FactionCommand();

        plugin.getCommand(factionCommand.getName()).setExecutor(factionCommand);
    }

    protected static void registerListeners(CFaction plugin) {
        plugin.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new FactionListener(), plugin);
    }

    protected static void copyResourcesFoldersIfNotExists(CFaction plugin) {
        File langFolder = new File(plugin.getDataFolder(), "lang/");

        if (!langFolder.exists()) {
            plugin.saveResource("lang/en-US.yml", false);
            plugin.saveResource("lang/fr-FR.yml", false);
        }
    }
}

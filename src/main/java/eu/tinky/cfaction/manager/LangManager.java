package eu.tinky.cfaction.manager;

import eu.tinky.cfaction.CFaction;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LangManager {

    private final FileConfiguration languageFileConfiguration;
    private final CFaction plugin = CFaction.getInstance();

    public LangManager() throws InvalidConfigurationException {
        String selectedLanguage = this.plugin.getConfig().getString("language", "en-US");
        this.languageFileConfiguration = loadLanguageFile(selectedLanguage);
        if(languageFileConfiguration == null){
            throw new InvalidConfigurationException(String.format("The language configuration file %s cannot be found!", selectedLanguage));
        }
    }

    public FileConfiguration loadLanguageFile(String queriedLanguage){
        File selectedLanguageFile = new File(this.plugin.getDataFolder() + "/lang/" + queriedLanguage + ".yml");

        return YamlConfiguration.loadConfiguration(selectedLanguageFile);
    }

    public String getMessage(String key) {
        String defaultMessage = "Unknown message, contact an administrator";

        return ChatColor.translateAlternateColorCodes('&', this.languageFileConfiguration.getString(key, defaultMessage));
    }
}

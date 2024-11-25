package eu.tinky.cfaction.command.faction.subcommands;

import eu.tinky.cfaction.command.Argument;
import eu.tinky.cfaction.command.SubCommand;
import eu.tinky.cfaction.event.FactionCreateEvent;
import eu.tinky.cfaction.models.Faction;
import eu.tinky.cfaction.models.data.FPlayer;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

public class CreateFactionSubCommand extends SubCommand {

    public CreateFactionSubCommand(String name, String description, LinkedHashSet<Argument> arguments, String permission, boolean canConsoleUse) {
        super(name, description, arguments, permission, canConsoleUse);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Optional<FPlayer> optionalFPlayer = plugin.getGlobalManager().getPlayerManager().getFPlayerByUsername(sender.getName());

        if (args.length == 0 || !optionalFPlayer.isPresent()) {
            return;
        }

        FPlayer fPlayer = optionalFPlayer.get();
        String factionName = args[0];
        List<String> blacklistedFactionNames = this.plugin.getConfig().getStringList("blacklisted-faction-name");

        if (blacklistedFactionNames.contains(factionName)) {
            fPlayer.getPlayer().sendMessage(this.plugin.getLangManager().getMessage("blacklisted-faction-name"));
            return;
        }

        // TODO: Implement the faction creation logic, then trigger the associated event.

        Faction faction = new Faction(factionName, fPlayer.getId());

        this.plugin.getFactionDAO().createFaction(faction);
        fPlayer.setFaction(faction);

        FactionCreateEvent factionCreateEvent = new FactionCreateEvent(fPlayer, factionName);
        plugin.getServer().getPluginManager().callEvent(factionCreateEvent);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}

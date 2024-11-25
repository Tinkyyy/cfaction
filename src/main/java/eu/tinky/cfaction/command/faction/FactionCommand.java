package eu.tinky.cfaction.command.faction;

import eu.tinky.cfaction.CFaction;
import eu.tinky.cfaction.command.Argument;
import eu.tinky.cfaction.command.CustomCommand;
import eu.tinky.cfaction.command.SubCommand;
import eu.tinky.cfaction.command.faction.subcommands.CreateFactionSubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.*;

public class FactionCommand extends CustomCommand implements TabCompleter {

    public FactionCommand() {
        super("faction", CFaction.getInstance().getLangManager().getMessage("commands.faction.description"), "cfaction.command", false);
        this.registerSubCommands();
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        sender.sendMessage(this.getHelpMessage());
    }

    protected void registerSubCommands() {
        LinkedHashSet<Argument> noArgumentsRequired = new LinkedHashSet<>();

        LinkedHashSet<Argument> createFactionSubCommandArguments = new LinkedHashSet<>();
        Argument dungeonSubCommandArgument = new Argument(Collections.singletonList("name"), false);
        createFactionSubCommandArguments.add(dungeonSubCommandArgument);


        SubCommand createFactionSubCommand = new CreateFactionSubCommand(
                "create",
                this.langManager.getMessage("commands.faction.create.description"),
                createFactionSubCommandArguments,
                this.permission + ".create",
                false
        );

        this.subCommands.putIfAbsent(createFactionSubCommand.getName(), createFactionSubCommand);
    }
}

package eu.tinky.cfaction.command;

import eu.tinky.cfaction.CFaction;
import eu.tinky.cfaction.manager.LangManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"unused"})
public abstract class CustomCommand implements CommandExecutor, TabCompleter {

    protected final String name;
    protected final String description;
    protected final String permission;
    protected final boolean canConsoleUse;
    protected final LinkedHashMap<String, SubCommand> subCommands;
    protected CFaction plugin;
    protected LangManager langManager;

    public CustomCommand(String name, String description, String permission, boolean canConsoleUse) {
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;
        this.subCommands = new LinkedHashMap<>();
        this.plugin = CFaction.getInstance();
        this.langManager = this.plugin.getLangManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (!sender.hasPermission(this.permission)) {
            sender.sendMessage(this.langManager.getMessage("no-permission"));
            return true;
        }

        if (!this.canConsoleUse && !(sender instanceof Player)) {
            sender.sendMessage(this.langManager.getMessage("console"));
            return true;
        }

        if (args.length > 0 && this.subCommands.containsKey(args[0].toLowerCase())) {
            SubCommand subCommand = this.subCommands.get(args[0].toLowerCase());
            if (sender.hasPermission(subCommand.getPermission())) {
                subCommand.execute(sender, Arrays.copyOfRange(args, 1, args.length));
            } else {
                sender.sendMessage(this.langManager.getMessage("no-permission"));
            }
        } else {
            this.execute(sender, args);
        }
        return true;
    }


    protected abstract void execute(CommandSender sender, String[] args);

    protected String getHelpMessage() {
        StringBuilder helpBuilder = new StringBuilder();

        helpBuilder.append(ChatColor.translateAlternateColorCodes('&',
                this.plugin.getConfig().getString("header", ""))).append('\n');

        helpBuilder.append(ChatColor.translateAlternateColorCodes('&',this.description)).append('\n');

        for (SubCommand subCommand : this.subCommands.values()) {
            helpBuilder.append(" \n");
            String commandUsageLine =
                    String.format(
                            "%s /%s %s %s\n",
                            this.plugin.getConfig().getString("commands.usage.color"),
                            this.name,
                            subCommand.getName(),
                            subCommand.getArguments()
                                    .stream()
                                    .map(Argument::toString)
                                    .collect(Collectors.joining(" "))
                    );

            String commandInformationLine = String.format("%s    • %s&r\n", this.plugin.getConfig().getString("commands.description.color"), subCommand.getDescription());

            helpBuilder.append(ChatColor.translateAlternateColorCodes('&', commandUsageLine));
            helpBuilder.append(ChatColor.translateAlternateColorCodes('&', commandInformationLine));
        }

        helpBuilder.append(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("footer", "")));

        return helpBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isCanConsoleUse() {
        return canConsoleUse;
    }

    public Map<String, SubCommand> getSubCommands() {
        return subCommands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(this.subCommands.keySet());
        }

        String selectedSubCommandString = args[0];

        if (this.subCommands.containsKey(selectedSubCommandString)) {
            SubCommand selectedSubCommand = this.subCommands.get(selectedSubCommandString);

            return selectedSubCommand.onTabComplete(sender, Arrays.copyOfRange(args, 1, args.length));
        }

        return Collections.emptyList();
    }
}

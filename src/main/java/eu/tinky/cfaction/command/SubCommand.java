package eu.tinky.cfaction.command;

import eu.tinky.cfaction.CFaction;
import org.bukkit.command.CommandSender;

import java.util.LinkedHashSet;
import java.util.List;

public abstract class SubCommand {

    private String name;
    private String description;
    private LinkedHashSet<Argument> arguments;
    private String permission;
    private boolean canConsoleUse;
    protected CFaction plugin;

    public SubCommand(String name, String description, LinkedHashSet<Argument> arguments, String permission, boolean canConsoleUse) {
        this.name = name;
        this.description = description;
        this.arguments = arguments;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;
        this.plugin = CFaction.getInstance();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedHashSet<Argument> getArguments() {
        return arguments;
    }

    public void setArguments(LinkedHashSet<Argument> arguments) {
        this.arguments = arguments;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isCanConsoleUse() {
        return canConsoleUse;
    }

    public void setCanConsoleUse(boolean canConsoleUse) {
        this.canConsoleUse = canConsoleUse;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public abstract List<String> onTabComplete(CommandSender sender, String[] args);
}

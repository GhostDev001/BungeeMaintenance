package de.mj.bungeemaintenance.commands;

import de.mj.bungeemaintenance.BungeeMaintenance;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MaintenanceCommand extends Command {

    private final BungeeMaintenance bungeeMaintenance;

    public MaintenanceCommand(BungeeMaintenance bungeeMaintenance) {
        super("maintenance", "maintenance.toggle");
        this.bungeeMaintenance = bungeeMaintenance;
        bungeeMaintenance.registerCommand(this);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(new TextComponent(bungeeMaintenance.getData().getToggleState().replace("%STATE%", bungeeMaintenance.getData().getStateMSG())));
        } else if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("enable")) {
                if (bungeeMaintenance.getData().isEnabled())
                    commandSender.sendMessage(new TextComponent(bungeeMaintenance.getData().getAllreadyEnabled()));
                else {
                    bungeeMaintenance.getData().setEnabled(true);
                    bungeeMaintenance.getFileManager().setMaintenanceMode(true);
                    for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                        if (!all.hasPermission("maintenance.join"))
                            all.disconnect(new TextComponent(bungeeMaintenance.getData().getDisconnectMSG()));
                    }
                    commandSender.sendMessage(new TextComponent(bungeeMaintenance.getData().getNowEnabled()));
                }
            } else if (strings[0].equalsIgnoreCase("disable")) {
                if (!bungeeMaintenance.getData().isEnabled())
                    commandSender.sendMessage(new TextComponent(bungeeMaintenance.getData().getAllreadyDisabled()));
                else {
                    bungeeMaintenance.getData().setEnabled(false);
                    bungeeMaintenance.getFileManager().setMaintenanceMode(false);
                    commandSender.sendMessage(new TextComponent(bungeeMaintenance.getData().getNowDisabled()));
                }
            } else commandSender.sendMessage(new TextComponent(bungeeMaintenance.getData() + " §cWrong usage! Please use /maintenance <enable/disable>"));
        } else commandSender.sendMessage(new TextComponent(bungeeMaintenance.getData() + " §cWrong usage! Please use /maintenance <enable/disable>"));
    }
}


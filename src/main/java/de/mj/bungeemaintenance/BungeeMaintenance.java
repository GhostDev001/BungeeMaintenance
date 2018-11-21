package de.mj.bungeemaintenance;

import de.mj.bungeemaintenance.commands.MaintenanceCommand;
import de.mj.bungeemaintenance.listeners.LoginListener;
import de.mj.bungeemaintenance.managers.FileManager;
import de.mj.bungeemaintenance.utils.Data;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMaintenance extends Plugin {

    private Data data;
    private FileManager fileManager;

    public void onEnable() {
        CommandSender sender = this.getProxy().getConsole();
        sender.sendMessage(new TextComponent(this.getDescription().getName() + " Plugin is loading..."));
        sender.sendMessage(new TextComponent(this.getDescription().getName() + " Plugin by " + this.getDescription().getAuthor()));
        sender.sendMessage(new TextComponent(this.getDescription().getName() + " Verison: " + this.getDescription().getVersion()));
        init();
        sender.sendMessage(new TextComponent(this.getDescription().getName() + " Maintenancemode is " + data.isEnabled()));
    }

    private void init() {
        data = new Data();
        fileManager = new FileManager(this);
        fileManager.createConfig();
        fileManager.loadConfig();
        fileManager.getDataFromConfig();
        new MaintenanceCommand(this);
        new LoginListener(this);
    }

    public void registerCommand(Command command) {
        this.getProxy().getPluginManager().registerCommand(this, command);
    }

    public void registerListener(Listener listener) {
        this.getProxy().getPluginManager().registerListener(this, listener);
    }

    public Data getData() {
        return data;
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}

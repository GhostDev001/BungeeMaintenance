package de.mj.bungeemaintenance.managers;

import de.mj.bungeemaintenance.BungeeMaintenance;
import de.mj.bungeemaintenance.utils.Data;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileManager {

    private final BungeeMaintenance bungeeMaintenance;
    private Configuration configuration;

    public FileManager (BungeeMaintenance bungeeMaintenance) {
        this.bungeeMaintenance = bungeeMaintenance;
    }

    public void createConfig() {
        if (!bungeeMaintenance.getDataFolder().exists())
            bungeeMaintenance.getDataFolder().mkdir();

        File file = new File(bungeeMaintenance.getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = bungeeMaintenance.getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadConfig() {
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(bungeeMaintenance.getDataFolder(), "config.yml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(bungeeMaintenance.getDataFolder(), "config.yml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void getDataFromConfig() {
        Data data = bungeeMaintenance.getData();
        data.setPrefix(configuration.getString("Prefix"));
        String prefix = data.getPrefix() + " ";
        data.setAllreadyDisabled(prefix + configuration.getString("AlreadyDisabled"));
        data.setAllreadyEnabled(prefix + configuration.getString("AlreadyEnabled"));
        data.setDisabledMSG(configuration.getString("DisabledMSG"));
        data.setDisconnectMSG(prefix + configuration.getString("DisconnectMSG"));
        data.setEnabledMSG(configuration.getString("EnabledMSG"));
        data.setNowDisabled(prefix + configuration.getString("NowDisabled"));
        data.setNowEnabled(prefix + configuration.getString("NowEnabled"));
        data.setToggleState(prefix + configuration.getString("State"));
        data.setEnabled(configuration.getBoolean("enabled"));
    }

    public void setMaintenanceMode (boolean state) {
        configuration.set("enabled", state);
        saveConfig();
    }
}


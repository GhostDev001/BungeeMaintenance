package de.mj.bungeemaintenance.listeners;

import de.mj.bungeemaintenance.BungeeMaintenance;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {

    private final BungeeMaintenance bungeeMaintenance;

    public LoginListener(BungeeMaintenance bungeeMaintenance) {
        this.bungeeMaintenance = bungeeMaintenance;
        bungeeMaintenance.registerListener(this);
    }

    @EventHandler
    public void onLogin(PostLoginEvent postLoginEvent) {
        ProxiedPlayer proxiedPlayer = postLoginEvent.getPlayer();
        if (bungeeMaintenance.getData().isEnabled())
            if (!proxiedPlayer.hasPermission("maintenance.join"))
                proxiedPlayer.disconnect(new TextComponent(bungeeMaintenance.getData().getDisconnectMSG()));
    }
}

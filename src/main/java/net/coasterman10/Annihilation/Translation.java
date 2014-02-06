package net.coasterman10.Annihilation;

import org.bukkit.ChatColor;

//Inspired from Essentials
public class Translation {
    public static String _ (String id) {
        return ChatColor.stripColor(Annihilation.messages.get(id));
    }
}

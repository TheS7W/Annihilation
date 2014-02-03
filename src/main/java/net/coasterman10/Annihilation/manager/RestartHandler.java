/*******************************************************************************
 * Copyright 2014 stuntguy3000 (Luke Anderson) and coasterman10.
 *  
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 ******************************************************************************/
package net.coasterman10.Annihilation.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.coasterman10.Annihilation.Annihilation;
import net.coasterman10.Annihilation.bar.BarUtil;

public class RestartHandler {
    private final Annihilation plugin;
    private long time;
    private long delay;
    private int taskID;

    public RestartHandler(Annihilation plugin, final long delay) {
        this.plugin = plugin;
        this.delay = delay;
    }

    public void start(final long gameTime) {
        time = delay;
        final String totalTime = PhaseManager.timeString(gameTime);
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,
                new Runnable() {
                    public void run() {
                        if (time <= 0) {
                            plugin.reset();
                            stop();
                            return;
                        }
                        String message = ChatColor.GOLD + "Total time: "
                                + ChatColor.WHITE + totalTime + " | "
                                + ChatColor.GREEN + "Restarting in "
                                + time;
                        float percent = (float) time / (float) delay;
                        for (Player p : Bukkit.getOnlinePlayers())
                            BarUtil.setMessageAndPercent(p, message, percent);
                        time--;
                    }
                }, 0L, 20L);
    }

    private void stop() {
        Bukkit.getScheduler().cancelTask(taskID);
    }
}

package net.moonfly.bcfallback;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

public final class BCfallback extends Plugin {

    @Override
    public void onEnable() {

        getProxy().getPluginManager().registerListener(this, new EventListener());
        getLogger().info(ChatColor.GREEN+"=============================================");
        getLogger().info(ChatColor.GREEN+" 插件已启动! - Auth：m00nfly   Ver：1.0");
        getLogger().info(ChatColor.YELLOW+"一个在子服断线后自动将玩家传回到 FallBack 子服的插件");
        getLogger().info(ChatColor.GREEN+"=============================================");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

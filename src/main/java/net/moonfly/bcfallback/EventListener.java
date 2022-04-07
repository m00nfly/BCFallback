package net.moonfly.bcfallback;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class EventListener implements Listener {
    Plugin plugin = ProxyServer.getInstance().getPluginManager().getPlugin("BCfallback");
    config config = new net.moonfly.bcfallback.config();
    //获取配置文件中是否需要给玩家发送传送消息的选项是否为 true
    boolean isNotify = config.getKey(plugin,"isNotify");
    boolean isReson = config.getKey(plugin,"isReson");
    String tarGetSRV = config.getString(plugin,"targetServer"); //获取目的子服

    @EventHandler
    //监听子服断线事件
    public void Event(ServerKickEvent event){
        String sRVInfo = event.getKickedFrom().getName();
        String Reason = BaseComponent.toLegacyText(event.getKickReasonComponent());
        ServerInfo tSRVInfo = ProxyServer.getInstance().getServerInfo(tarGetSRV);
        String msg = null; //定义传送后的消息内容

        //判断玩家不是从fallback 子服被踢时，才执行传送
        if(!sRVInfo.equals(tarGetSRV)){
            event.setCancelled(true); //阻断玩家客户端断开会话
            event.setCancelServer(tSRVInfo); //将玩家传送到设定过的大厅子服
            if (isNotify) {
                msg = ChatColor.GREEN + "[BCfallback] 已将您送回大厅！！";
                msg += "你已经从子服：[" + sRVInfo + "] 退出了！";
                if (isReson) {
                    msg += "原因：" + Reason;
                }
            }
            assert msg != null;
            event.getPlayer().sendMessage(ChatMessageType.SYSTEM, TextComponent.fromLegacyText(msg));
            event.getPlayer().sendMessage(ChatMessageType.ACTION_BAR,TextComponent.fromLegacyText(msg));
        }
    }
}

package gg.quartzdev.qselectionui.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TitleUtil {

    static int TITLE_LEN = 70;
    static int zTITLE_LEN = 35;
    static int SUBTITLE_LEN = 60;


    public static void show(Player player, String titleMsg, String subTitleMsg){
        MiniMessage mm = MiniMessage.miniMessage();
        String str = String.format("%-" + TITLE_LEN + "s", subTitleMsg);
        Bukkit.getLogger().info("<" + str + ">");
        Component comp = mm.deserialize(str);
        Title title = Title.title(Component.empty(), comp);
        player.showTitle(title);
    }


}

package grief.gorilla.ingotfarm;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AIngotFarmCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(!Global.PL.getConfig().contains(p.getUniqueId() + "")) {
                Global.PL.getConfig().set(p.getUniqueId() + "", false);
                p.sendMessage(ChatColor.AQUA + "Вы успешно " + ChatColor.RED + "выключили " + ChatColor.AQUA + "фармилку хорусов");
                return true;
            }

            if(Global.PL.getConfig().getBoolean(p.getUniqueId() + "") == false) {
                Global.PL.getConfig().set(p.getUniqueId() + "", true);
                p.sendMessage(ChatColor.AQUA + "Вы успешно " + ChatColor.GREEN + "включили " + ChatColor.AQUA + "фармилку хорусов");
                return true;
            }else {
                Global.PL.getConfig().set(p.getUniqueId() + "", false);
                p.sendMessage(ChatColor.AQUA + "Вы успешно " + ChatColor.RED + "выключили " + ChatColor.AQUA + "фармилку хорусов");
                return true;
            }
        }
        else {
            sender.sendMessage("Эту команду может использовать только игрок!");
            return true;
        }
    }
}

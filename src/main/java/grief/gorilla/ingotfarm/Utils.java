package grief.gorilla.ingotfarm;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Utils {

    public static ItemStack craftCrystall() {
        ItemStack dim = new ItemStack(Material.DIAMOND);
        ItemMeta dimm = dim.getItemMeta();
        dimm.setDisplayName(ChatColor.DARK_PURPLE + "Кристалл Хоруса");
        dimm.setLore(Arrays.asList("Каждую секунду выдает Хорус"));
        dim.setItemMeta(dimm);
        return dim;
    }

}

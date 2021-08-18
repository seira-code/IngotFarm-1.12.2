package grief.gorilla.ingotfarm;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

import static org.bukkit.Material.CHORUS_FLOWER;
import static org.bukkit.Material.STAINED_GLASS_PANE;

public final class IngotFarm extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("Diamond Ingots ready to give!");

        getServer().getPluginManager().registerEvents(this, this);
        getCommand("aingotfarm").setExecutor(new AIngotFarmCommand());

        Global.PL = this;
        getConfig().options().copyDefaults(true);
        saveConfig();

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, ()->{
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(p.getInventory().contains(Utils.craftCrystall()) && getConfig().getBoolean(p.getUniqueId() + "") == true) {
                    p.getInventory().addItem(new ItemStack(Material.CHORUS_FRUIT, 1));
                }
            }
        }, 0, 20);
    }

    @Override
    public void onDisable() {

    }


    @EventHandler
    public void onDiamondIngotClick(PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND) {
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("Diamond Ingot")) {
                Inventory DInv = Bukkit.createInventory(p, 9, ChatColor.DARK_PURPLE + "Алмазный Слиток Хоруса");

                ItemStack it = new ItemStack(STAINED_GLASS_PANE, 1);
                ItemStack ch = new ItemStack(CHORUS_FLOWER, 1);
                ItemMeta im = it.getItemMeta(); im.setDisplayName(ChatColor.RED + "Кликни по цветку Хоруса"); it.setItemMeta(im);
                ItemMeta im2 = ch.getItemMeta();

                if(p.getInventory().contains(Material.CHORUS_FLOWER)) {
                    im2.setDisplayName(ChatColor.RED + "Кликни по Мне!");
                }else{
                    im2.setDisplayName(ChatColor.RED + "У тебя в инвентаре нет Цветка Хоруса!");
                }
                ch.setItemMeta(im2);


                DInv.setItem(0, it); DInv.setItem(1, it); DInv.setItem(2, it); DInv.setItem(3, it); DInv.setItem(4, ch);
                DInv.setItem(5, it); DInv.setItem(6, it); DInv.setItem(7, it); DInv.setItem(8, it);

                p.openInventory(DInv);
            }
        }
    }

    @EventHandler
    public void onDiamondIngotInventoryClick(InventoryClickEvent e) {
       if(e.getClickedInventory() != null && ChatColor.stripColor(e.getClickedInventory().getName()).equals("Алмазный Слиток Хоруса")) {
            e.setCancelled(true);
             switch(e.getCurrentItem().getType()) {
                 case CHORUS_FLOWER:
                     Player p = (Player) e.getWhoClicked();
                     if(p.getInventory().contains(Material.CHORUS_FLOWER)) {
                         p.closeInventory();
                         p.sendMessage(ChatColor.GREEN + "Теперь это Кристалл Хоруса, он будет выдавать вам " + ChatColor.AQUA + "Хорусы каждую секунду.\n" + ChatColor.GREEN + "Если у вас будет больше чем 1 Кристалл в Слоту ничего не будет происходить\n" + ChatColor.GOLD + "Что бы выключить это, напишите команду /aif");
                         ItemStack dim = new ItemStack(Material.DIAMOND);
                         ItemMeta dimm = dim.getItemMeta();
                         dimm.setDisplayName("Diamond Ingot");
                         dim.setItemMeta(dimm);
                         p.getInventory().removeItem(dim); p.getInventory().removeItem(new ItemStack(CHORUS_FLOWER, 1));

                         p.getInventory().addItem(Utils.craftCrystall());
                         break;
                     }
             }
            }
        }
    }




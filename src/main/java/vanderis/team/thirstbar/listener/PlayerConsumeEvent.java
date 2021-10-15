package vanderis.team.thirstbar.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import vanderis.team.thirstbar.manager.StorageMethod;
import vanderis.team.thirstbar.manager.thirst.PlayersThirstList;
import vanderis.team.thirstbar.manager.thirst.PlayersThirst;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerConsumeEvent implements Listener {

    public static final HashMap<Player, Double> listRegenThirst = new HashMap<>();
    public static final HashMap<Player, String> listNameItemConsume = new HashMap<>();

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        listNameItemConsume.put(player, item.getType().toString());
        if (StorageMethod.plugin.getConfig().getBoolean("ReplaceFood") &&
                !StorageMethod.listItemConsume.stream().map(s -> s.toUpperCase().split(":")[0])
                        .collect(Collectors.toList()).contains(item.getType().toString()))
            e.setCancelled(true);
        for (String list : StorageMethod.listItemConsume) {
            if (list.split(":").length != 2) continue;
            String itemConsume = list.split(":")[0].toUpperCase().trim();
            if (!item.getType().equals(Material.getMaterial(itemConsume))) continue;
            String valueS = list.split(":")[1].toUpperCase().trim();
            if (!StorageMethod.checkConvertDouble(valueS)) valueS = "1";
            double value = Double.parseDouble(valueS);
            PlayersThirst thirstPlayer = PlayersThirstList.getThirstPlayer(player);
            double cal = thirstPlayer.getThirstValue() + value;
            thirstPlayer.setThirstValue(Math.min(thirstPlayer.getThirstMax(), cal));
            PlayersThirstList.changePlayerBossbar(thirstPlayer);
            PlayersThirstList.setEffectThirst(thirstPlayer);
            listRegenThirst.put(player, value);
            break;
        }
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        if (e.useItemInHand().equals(Event.Result.DENY)) return;
        //
        if (e.getAction() == Action.PHYSICAL) return;
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = e.getPlayer();
            if (!e.useInteractedBlock().equals(Event.Result.DENY)) {
                if (e.getClickedBlock() != null
                        && e.getAction() == Action.RIGHT_CLICK_BLOCK
                        && !player.isSneaking()) {// Having both of these checks is useless, might as well do it though.
                    // Some blocks have actions when you right click them which stops the client from equipping the armor in hand.
                    Material mat = e.getClickedBlock().getType();
                    for (String s : listBlock()) {
                        if (mat.name().equalsIgnoreCase(s)) return;
                    }
                }
            }
        }

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = e.getPlayer();
            ItemStack item = e.getItem();
            if (item == null || !item.getType().isEdible()) return;
            if (player.getFoodLevel() != 20) return;
            player.setFoodLevel(19);
            Bukkit.getScheduler().scheduleSyncDelayedTask(StorageMethod.plugin, () -> player.setFoodLevel(20), 1);
        }
    }

    @EventHandler
    public void onHungerDecrease(FoodLevelChangeEvent e){
        if(StorageMethod.plugin.getConfig().getBoolean("ReplaceFood")) e.setCancelled(true);
    }

    private List<String> listBlock() {
        return Arrays.asList("FURNACE", "CHEST", "TRAPPED_CHEST", "BEACON", "DISPENSER", "DROPPER", "HOPPER"
                , "WORKBENCH", "ENCHANTMENT_TABLE", "ENDER_CHEST", "ANVIL", "BED_BLOCK", "FENCE_GATE"
                , "SPRUCE_FENCE_GATE", "BIRCH_FENCE_GATE", "ACACIA_FENCE_GATE", "JUNGLE_FENCE_GATE"
                , "DARK_OAK_FENCE_GATE", "IRON_DOOR_BLOCK", "WOODEN_DOOR", "SPRUCE_DOOR", "BIRCH_DOOR"
                , "JUNGLE_DOOR", "ACACIA_DOOR", "DARK_OAK_DOOR", "WOOD_BUTTON"
                , "STONE_BUTTON", "TRAP_DOOR", "IRON_TRAPDOOR", "DIODE_BLOCK_OFF"
                , "DIODE_BLOCK_ON", "REDSTONE_COMPARATOR_OFF", "REDSTONE_COMPARATOR_ON"
                , "FENCE", "SPRUCE_FENCE", "BIRCH_FENCE", "JUNGLE_FENCE"
                , "DARK_OAK_FENCE", "ACACIA_FENCE", "NETHER_FENCE", "BREWING_STAND"
                , "CAULDRON", "LEGACY_SIGN_POST", "LEGACY_WALL_SIGN", "LEGACY_SIGN"
                , "ACACIA_SIGN", "ACACIA_WALL_SIGN", "BIRCH_SIGN", "BIRCH_WALL_SIGN"
                , "DARK_OAK_SIGN", "DARK_OAK_WALL_SIGN", "JUNGLE_SIGN", "JUNGLE_WALL_SIGN"
                , "OAK_SIGN", "OAK_WALL_SIGN", "SPRUCE_SIGN", "SPRUCE_WALL_SIGN"
                , "LEVER", "BLACK_SHULKER_BOX", "BLUE_SHULKER_BOX", "BROWN_SHULKER_BOX"
                , "CYAN_SHULKER_BOX", "GRAY_SHULKER_BOX", "GREEN_SHULKER_BOX", "LIGHT_BLUE_SHULKER_BOX"
                , "LIME_SHULKER_BOX", "MAGENTA_SHULKER_BOX", "ORANGE_SHULKER_BOX", "PINK_SHULKER_BOX"
                , "PURPLE_SHULKER_BOX", "RED_SHULKER_BOX", "SILVER_SHULKER_BOX", "WHITE_SHULKER_BOX"
                , "YELLOW_SHULKER_BOX", "DAYLIGHT_DETECTOR_INVERTED", "DAYLIGHT_DETECTOR", "BARREL"
                , "BLAST_FURNACE", "SMOKER", "CARTOGRAPHY_TABLE", "COMPOSTER"
                , "GRINDSTONE", "LECTERN", "LOOM", "STONECUTTER", "BELL");
    }

}

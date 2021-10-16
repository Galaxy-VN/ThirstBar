package vanderis.team.thirstbar.manager.thirst;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.manager.StorageMethod;

@SuppressWarnings("unused")
public class PlayersThirst {

    private final Player player;
    private double thirstMax;
    private double thirstValue;
    private double thirstDecrease;
    private boolean immune;
    private boolean disable;
    private BossBar bossBar;
    private int idRepeat;
    private int locSection = -2;

    public PlayersThirst(Player player, double thirstMax, double thirstDecrease) {
        this.player = player;
        this.thirstMax = thirstMax;
        this.thirstValue = thirstMax;
        this.thirstDecrease = thirstDecrease;
    }

    public PlayersThirst(Player player) {
        this.player = player;
        this.thirstMax = StorageMethod.plugin.getConfig().getDouble("Thirst.Max");
        this.thirstValue = StorageMethod.plugin.getConfig().getDouble("Thirst.Max");
        this.thirstDecrease = StorageMethod.plugin.getConfig().getDouble("Thirst.DecreasePerTime");
    }

    public Player getPlayer() {
        return player;
    }

    public double getThirstMax() {
        return thirstMax;
    }

    public void setThirstMax(double thirstMax) {
        this.thirstMax = thirstMax;
    }

    public double getThirstValue() {
        return thirstValue;
    }

    public void setThirstValue(double thirstValue) {
        this.thirstValue = thirstValue;
    }

    public double getThirstDecrease() {
        return thirstDecrease;
    }

    public void setThirstDecrease(double thirstDecrease) {
        this.thirstDecrease = thirstDecrease;
    }

    public int getLocSection() {
        return locSection;
    }

    public void setLocSection(int locSection) {
        this.locSection = locSection;
    }

    @SuppressWarnings("all")
    public boolean isImmune() {
        return immune;
    }

    public void setImmune(boolean immune) {
        this.immune = immune;
    }

    @SuppressWarnings("all")
    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public void setBossBar(BossBar bossBar) {
        this.bossBar = bossBar;
    }

    public int getIdRepeat() {
        return idRepeat;
    }

    public void setIdRepeat(int idRepeat) {
        this.idRepeat = idRepeat;
    }
}

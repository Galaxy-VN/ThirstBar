package vanderis.team.thirstbar.manager.thirst;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.manager.Method;

@SuppressWarnings("unused")
public class ThirstPlayer {

    private final Player player;
    private double thirstMax;
    private double thirstPoint;
    private double thirstDecrease;
    private boolean immune;
    private boolean disable;
    private BossBar bossBar;
    private int idRepeat;
    private int locSection = -2;

    public ThirstPlayer(Player player, double thirstMax, double thirstDecrease) {
        this.player = player;
        this.thirstMax = thirstMax;
        this.thirstPoint = thirstMax;
        this.thirstDecrease = thirstDecrease;
    }

    public ThirstPlayer(Player player) {
        this.player = player;
        this.thirstMax = Method.plugin.getConfig().getDouble("Thirst.Max");
        this.thirstPoint = Method.plugin.getConfig().getDouble("Thirst.Max");
        this.thirstDecrease = Method.plugin.getConfig().getDouble("Thirst.DecreasePerTime");
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

    public double getThirstPoint() {
        return thirstPoint;
    }

    public void setThirstPoint(double thirstPoint) {
        this.thirstPoint = thirstPoint;
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

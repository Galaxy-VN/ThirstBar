package vanderis.team.thirstbar.manager.water;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import vanderis.team.thirstbar.manager.Method;

@SuppressWarnings("unused")
public class ThirstPlayer {

    private final Player player;
    private double waterMax;
    private double waterPoint;
    private double waterDecrease;
    private boolean immune;
    private boolean disable;
    private BossBar bossBar;
    private int idRepeat;
    private int locSection = -2;

    public ThirstPlayer(Player player, double waterMax, double waterDecrease) {
        this.player = player;
        this.waterMax = waterMax;
        this.waterPoint = waterMax;
        this.waterDecrease = waterDecrease;
    }

    public ThirstPlayer(Player player){
        this.player = player;
        this.waterMax = Method.plugin.getConfig().getDouble("Water.Max");
        this.waterPoint = Method.plugin.getConfig().getDouble("Water.Max");
        this.waterDecrease = Method.plugin.getConfig().getDouble("Water.DecreasePerTime");
    }

    public Player getPlayer() {
        return player;
    }

    public double getWaterMax() {
        return waterMax;
    }

    public double getWaterPoint() {
        return waterPoint;
    }

    public double getWaterDecrease() {
        return waterDecrease;
    }

    public int getLocSection(){ return locSection; }

    @SuppressWarnings("all")
    public boolean isImmune(){
        return immune;
    }

    @SuppressWarnings("all")
    public boolean isDisable(){
        return disable;
    }

    public BossBar getBossBar(){ return bossBar; }

    public int getIdRepeat(){ return idRepeat; }

    public void setWaterMax(double waterMax) {
        this.waterMax = waterMax;
    }

    public void setWaterPoint(double waterPoint) {
        this.waterPoint = waterPoint;
    }

    public void setWaterDecrease(double waterDecrease) {
        this.waterDecrease = waterDecrease;
    }

    public void setLocSection(int locSection) { this.locSection = locSection; }

    public void setImmune(boolean immune){
        this.immune = immune;
    }

    public void setDisable(boolean disable){
        this.disable = disable;
    }

    public void setBossBar(BossBar bossBar){ this.bossBar = bossBar; }

    public void setIdRepeat(int idRepeat){ this.idRepeat = idRepeat; }
}

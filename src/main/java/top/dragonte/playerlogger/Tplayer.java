package top.dragonte.playerlogger;

import net.minecraft.server.network.ServerPlayerEntity;

public class Tplayer {
    public ServerPlayerEntity player;
    public long loginTime;
    public long logoutTime;
    public long stayTime;


    public Tplayer(ServerPlayerEntity player) {
        this.player = player;
    }

    public void login() {
        loginTime = System.currentTimeMillis();
    }

    public void logout() {
        logoutTime = System.currentTimeMillis();
        stayTime = logoutTime - loginTime;
    }

    public long stayMinutes() {
        return Math.round((float) stayTime / 1000 / 60) ;
    }
    public String getName() {
        return String.valueOf(player.getName()).substring(8).replace("}", "");
    }

    public String getIP() {
        return player.getIp();
    }
}

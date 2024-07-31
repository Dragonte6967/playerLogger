package top.dragonte.playerlogger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Playerlogger implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("PlayerLogger");
    HashMap<ServerPlayerEntity,Tplayer> players = new HashMap<>();
    @Override
    public void onInitialize() {
        LOGGER.info("玩家记录插件开始运行");
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            Tplayer tplayer = new Tplayer(player);
            players.put(player,tplayer);
            tplayer.login();
        });
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            Tplayer tplayer = players.get(player);
            tplayer.logout();
            Tlog tlog = new Tlog(tplayer);
            tlog.log();
            players.remove(player);
        });
    }
}

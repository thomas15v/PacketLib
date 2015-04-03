package com.thomas15v.testPlugin;

import com.thomas15v.packetlib.api.event.Event;
import com.thomas15v.packetlib.api.event.PacketListener;
import com.thomas15v.packetlib.api.packet.play.shared.PacketUpdateSign;
import com.thomas15v.packetlib.api.service.IPacketService;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.state.ServerStartedEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.util.event.Subscribe;

/**
 * Needs to be removed, is just for testing propose!
 */
@Plugin(name = "Test", id = "Test", version = "0.0.1")
public class TestPlugin {

    @Subscribe
    public void onServerStarted(ServerStartedEvent event) throws Exception {
        event.getGame().getServiceManager().provide(IPacketService.class).get().getPacketManager().register(new PacketListener<PacketUpdateSign>() {

            @Override
            public void onPacket(Event<PacketUpdateSign> event) {
                System.out.println(event.getPacket() + " " + ((Player) event.getConnectionUser()).getName());
            }
        });

        event.getGame().getServiceManager().provide(IPacketService.class).get().getPacketManager().register(new PacketListener() {

            @Override
            public void onPacket(Event event) {

            }
        });
    }
}

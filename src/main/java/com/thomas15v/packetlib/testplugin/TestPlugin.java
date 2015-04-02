package com.thomas15v.packetlib.testplugin;

import com.thomas15v.packetlib.api.event.play.shared.PacketUpdateSignEvent;
import org.spongepowered.api.event.state.ServerStartedEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.util.event.Subscribe;

/**
 * Needs to be removed, is just for testing propose!
 */
@Plugin(name = "Test", id = "Test", version = "0.0.1")
public class TestPlugin {

    @Subscribe
    public void onServerStarted(ServerStartedEvent event) {
        event.getGame().getEventManager().register(this, this);
    }

    @Subscribe
    public void onSignUpdate(PacketUpdateSignEvent event){
        System.out.print(event.getPacket());
    }

}

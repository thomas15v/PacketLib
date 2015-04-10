package com.thomas15v.testPlugin;

import com.thomas15v.packetlib.api.event.IPacketManager;
import com.thomas15v.packetlib.api.service.IPacketService;
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
        IPacketService service = event.getGame().getServiceManager().provide(IPacketService.class).get();
        IPacketManager packetManager = service.getPacketManager();
        packetManager.register(new PacketTransformer());
        System.out.println("testplugin started!, expect masive spam!!!!");
    }
}

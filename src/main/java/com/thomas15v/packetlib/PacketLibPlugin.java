package com.thomas15v.packetlib;

import com.thomas15v.packetlib.api.service.IPacketService;
import com.thomas15v.packetlib.impl.service.PacketService;
import org.spongepowered.api.event.state.ServerStartedEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.ProviderExistsException;
import org.spongepowered.api.util.event.Subscribe;

/**
 * Created by thomas15v on 2/04/15.
 */
@Plugin(name = "PacketLib", id = "PacketLib", version = "0.0.1")
public class PacketLibPlugin {

    @Subscribe
    public void onServerStarted(ServerStartedEvent event) throws ProviderExistsException {
        event.getGame().getServiceManager().setProvider(this, IPacketService.class, new PacketService());
    }

}

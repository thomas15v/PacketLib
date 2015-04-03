package com.thomas15v.packetlib.impl.util;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.packet.Packet;
import com.thomas15v.packetlib.api.service.IPacketService;
import org.spongepowered.mod.SpongeMod;

/**
 * Created by thomas15v on 1/04/15.
 */
public class EventUtil {

    public static boolean postEventFor(Packet packet, ConnectionUser player){
        IPacketService eventManager =  SpongeMod.instance.getGame().getServiceManager().provide(IPacketService.class).get();
        return eventManager.getPacketManager().post(packet, player);
    }
}

package com.thomas15v.packetlib.impl.util;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.packet.play.PlayPacket;
import org.spongepowered.api.service.event.EventManager;
import org.spongepowered.mod.SpongeMod;

/**
 * Created by thomas15v on 1/04/15.
 */
public class EventUtil {

    public static void postEventFor(PlayPacket packet, ConnectionUser player){
        EventManager eventManager =  SpongeMod.instance.getGame().getEventManager();
        eventManager.post(packet.getEvent(player));
    }

    public static void postEventFor(PlayPacket packet){

    }

}

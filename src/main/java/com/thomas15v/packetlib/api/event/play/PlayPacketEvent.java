package com.thomas15v.packetlib.api.event.play;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.Packetevent;
import com.thomas15v.packetlib.api.packet.Packet;
import com.thomas15v.packetlib.api.packet.play.PlayPacket;
import org.spongepowered.api.entity.player.Player;

/**
 * Created by thomas15v on 1/04/15.
 */
public class PlayPacketEvent<P extends PlayPacket> extends Packetevent<P> {


    public PlayPacketEvent(P packet, ConnectionUser connectionUser) {
        super(packet, connectionUser);
    }

    public Player getPlayer(){
        return (Player) getConnectionUser();
    }
}

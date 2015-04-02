package com.thomas15v.packetlib.api.event.play.client;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.play.PlayPacketEvent;
import com.thomas15v.packetlib.api.packet.play.client.PacketAnimation;

/**
 * Created by thomas15v on 1/04/15.
 */
public class PacketAnimationEvent extends PlayPacketEvent<PacketAnimation> {

    public PacketAnimationEvent(PacketAnimation packet, ConnectionUser connectionUser) {
        super(packet, connectionUser);
    }
}

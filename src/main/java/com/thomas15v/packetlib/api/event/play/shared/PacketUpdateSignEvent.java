package com.thomas15v.packetlib.api.event.play.shared;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.play.PlayPacketEvent;
import com.thomas15v.packetlib.api.packet.play.shared.PacketUpdateSign;

/**
 * Created by thomas15v on 1/04/15.
 */
public class PacketUpdateSignEvent extends PlayPacketEvent<PacketUpdateSign> {

    public PacketUpdateSignEvent(PacketUpdateSign packet, ConnectionUser connectionUser) {
        super(packet, connectionUser);
    }
}

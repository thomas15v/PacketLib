package com.thomas15v.packetlib.api.event.play.server;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.play.PlayPacketEvent;
import com.thomas15v.packetlib.api.packet.play.server.PacketcollectItem;

/**
 * Created by thomas15v on 1/04/15.
 */
public class PacketCollectItemEvent extends PlayPacketEvent<PacketcollectItem> {

    public PacketCollectItemEvent(PacketcollectItem packet, ConnectionUser connectionUser) {
        super(packet, connectionUser);
    }
}

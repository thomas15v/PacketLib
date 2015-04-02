package com.thomas15v.packetlib.api.event.play.client;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.play.PlayPacketEvent;
import com.thomas15v.packetlib.api.packet.play.client.PacketEntityAction;

/**
 * Created by thomas15v on 1/04/15.
 */
public class PacketEntityActionEvent extends PlayPacketEvent<PacketEntityAction> {
    public PacketEntityActionEvent(PacketEntityAction packet, ConnectionUser connectionUser) {
        super(packet, connectionUser);
    }
}

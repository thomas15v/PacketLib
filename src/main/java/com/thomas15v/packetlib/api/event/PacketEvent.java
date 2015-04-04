package com.thomas15v.packetlib.api.event;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.packet.Packet;
import org.spongepowered.api.util.event.Cancellable;

/**
 * Created by thomas15v on 2/04/15.
 */
public interface PacketEvent<P extends Packet> {

    ConnectionUser getConnectionUser();

    P getPacket();

    boolean isType(Class<? extends Packet> clazz);

}

package com.thomas15v.packetlib.api.event;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.packet.Packet;
import org.spongepowered.api.util.event.Cancellable;

/**
 * Event that contains the ConnectionUser and the packet. Gets called when a packet gets recieved or send.
 * @param <P>
 */
public interface PacketEvent<P extends Packet> {

    ConnectionUser getConnectionUser();

    P getPacket();

    boolean isType(Class<? extends Packet> clazz);

}

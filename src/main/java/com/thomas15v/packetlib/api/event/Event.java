package com.thomas15v.packetlib.api.event;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.packet.Packet;

/**
 * Created by thomas15v on 2/04/15.
 */
public interface Event<P extends Packet> {

    ConnectionUser getConnectionUser();

    P getPacket();

}

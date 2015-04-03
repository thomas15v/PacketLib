package com.thomas15v.packetlib.api.event;

import com.thomas15v.packetlib.api.packet.Packet;

/**
 * Created by thomas15v on 2/04/15.
 */
public interface PacketListener<I extends Packet> {

    public void onPacket(Event<I> event);

}

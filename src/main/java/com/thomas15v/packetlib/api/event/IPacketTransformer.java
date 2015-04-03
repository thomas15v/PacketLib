package com.thomas15v.packetlib.api.event;

import com.thomas15v.packetlib.api.packet.Packet;

/**
 * Created by thomas15v on 3/04/15.
 */
public interface IPacketTransformer<I extends Packet> {

    public void onPacket(PacketEvent<I> event);

    /**
     *
     * @return an array of packet instance you want to listen to
     */
    public Class[] forPackets();

}

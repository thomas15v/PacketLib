package com.thomas15v.packetlib.api.packet.play;

import com.thomas15v.packetlib.api.packet.State;
import com.thomas15v.packetlib.api.packet.Packet;

/**
 * Created by thomas15v on 1/04/15.
 */
public interface PlayPacket extends Packet {

    @Override
    public State getState();
}

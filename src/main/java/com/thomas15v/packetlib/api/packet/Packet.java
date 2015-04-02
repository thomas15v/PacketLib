package com.thomas15v.packetlib.api.packet;

/**
 * Created by thomas15v on 31/03/15.
 */
public interface Packet {
    State getState();

    Bound getBound();
}

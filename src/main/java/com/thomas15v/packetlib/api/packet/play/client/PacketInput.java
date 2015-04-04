package com.thomas15v.packetlib.api.packet.play.client;

import com.thomas15v.packetlib.api.packet.Packet;

/**
 * Created by thomas15v on 3/04/15.
 */
public interface PacketInput extends Packet {

    float getStrafeSpeed();

    void setStrafeSpeed(float strafeSpeed);

    float getForwardSpeed();

    void setForwardSpeed(float forwardSpeed);

    boolean getJumping();

    void setJumping(boolean jumping);

    boolean getSneaking();

    void setSneaking(boolean sneaking);
}

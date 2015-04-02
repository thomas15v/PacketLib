package com.thomas15v.packetlib.api.packet.play;


import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.packet.State;
import com.thomas15v.packetlib.api.event.play.PlayPacketEvent;
import com.thomas15v.packetlib.api.packet.Packet;

/**
 * Created by thomas15v on 1/04/15.
 */
public abstract class PlayPacket implements Packet {

    public abstract PlayPacketEvent getEvent(ConnectionUser player);

    @Override
    public State getState(){
        return State.Play;
    }
}

package com.thomas15v.packetlib.impl.event;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.Event;
import com.thomas15v.packetlib.api.packet.Packet;

/**
 * Created by thomas15v on 2/04/15.
 */
public class PlayEvent implements Event {

    private Packet packet;
    private ConnectionUser connectionUser;

    public PlayEvent(Packet packet, ConnectionUser connectionUser){
        this.packet = packet;
        this.connectionUser = connectionUser;
    }

    @Override
    public ConnectionUser getConnectionUser() {
        return connectionUser;
    }

    @Override
    public Packet getPacket() {
        return packet;
    }
}

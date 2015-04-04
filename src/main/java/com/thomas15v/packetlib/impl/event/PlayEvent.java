package com.thomas15v.packetlib.impl.event;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.PacketEvent;
import com.thomas15v.packetlib.api.packet.Packet;

/**
 * Created by thomas15v on 2/04/15.
 */
public class PlayEvent<P extends Packet> implements PacketEvent<P> {

    private P packet;
    private ConnectionUser connectionUser;
    private boolean cancel;

    public PlayEvent(P packet, ConnectionUser connectionUser){
        this.packet = packet;
        this.connectionUser = connectionUser;
    }

    @Override
    public ConnectionUser getConnectionUser() {
        return connectionUser;
    }

    @Override
    public P getPacket() {
        return packet;
    }

    @Override
    public boolean isType(Class clazz) {
        return clazz.isInstance(getPacket());
    }


    //todo: fix canceling events, may be a mixin issues.
    //@Override
    public boolean isCancelled() {
        return cancel;
    }

    //@Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}

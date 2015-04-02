package com.thomas15v.packetlib.api.event;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.packet.Packet;
import org.spongepowered.api.event.AbstractEvent;
import org.spongepowered.api.util.event.Cancellable;

public class Packetevent<P extends Packet> extends AbstractEvent implements Cancellable {

    protected P packet;
    private ConnectionUser connectionUser;
    private boolean cancel = false;

    public Packetevent(P packet, ConnectionUser connectionUser){
        this.packet = packet;
        this.connectionUser = connectionUser;
    }

    public P getPacket() {
        return packet;
    }

    public boolean hasPacket(){
        return getPacket() != null;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public ConnectionUser getConnectionUser() {
        return connectionUser;
    }
}
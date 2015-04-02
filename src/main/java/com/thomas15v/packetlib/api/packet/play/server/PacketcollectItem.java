package com.thomas15v.packetlib.api.packet.play.server;

import com.thomas15v.packetlib.api.packet.play.PlayPacket;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.Item;

public abstract class PacketcollectItem extends PlayPacket {

    public abstract Entity getEntity();

    public abstract int getEntityId();

    public abstract Item getItem();

    public abstract int getItemEntityId();
}

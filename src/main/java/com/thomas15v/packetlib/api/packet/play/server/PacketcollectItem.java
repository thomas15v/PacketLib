package com.thomas15v.packetlib.api.packet.play.server;

import com.thomas15v.packetlib.api.packet.play.PlayPacket;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.Item;

public interface PacketcollectItem extends PlayPacket {

    public Entity getEntity();

    public int getEntityId();

    public Item getItem();

    public int getItemEntityId();
}

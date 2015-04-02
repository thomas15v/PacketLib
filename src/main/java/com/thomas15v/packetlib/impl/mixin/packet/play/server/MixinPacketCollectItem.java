package com.thomas15v.packetlib.impl.mixin.packet.play.server;

import com.thomas15v.packetlib.api.packet.Bound;
import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.play.PlayPacketEvent;
import com.thomas15v.packetlib.api.event.play.server.PacketCollectItemEvent;
import com.thomas15v.packetlib.api.packet.play.server.PacketcollectItem;
import com.thomas15v.packetlib.impl.util.EntityUtil;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by thomas15v on 31/03/15.
 */
@Mixin(S0DPacketCollectItem.class)
public class MixinPacketCollectItem extends PacketcollectItem {

    @Shadow
    private int collectedItemEntityId;

    @Shadow
    private int entityId;

    @Override
    public Entity getEntity() {
        Object entity = EntityUtil.getEntityObject(entityId);
        if (entity instanceof Entity)
            return (Entity) entity;
        return null;
    }

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public Item getItem() {
        Object entity = EntityUtil.getEntityObject(collectedItemEntityId);
        if (entity instanceof Item)
            return (Item) entity;
        return null;
    }

    @Override
    public int getItemEntityId() {
        return collectedItemEntityId;
    }

    @Override
    public Bound getBound() {
        return Bound.Server;
    }

    @Override
    public PlayPacketEvent getEvent(ConnectionUser player) {
        return new PacketCollectItemEvent(this, player);
    }
}

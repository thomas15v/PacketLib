package com.thomas15v.packetlib.impl.mixin.packet.play.client;

import com.thomas15v.packetlib.api.packet.Bound;
import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.play.PlayPacketEvent;
import com.thomas15v.packetlib.api.event.play.client.PacketEntityActionEvent;
import com.thomas15v.packetlib.api.packet.Packet;
import com.thomas15v.packetlib.api.packet.State;
import com.thomas15v.packetlib.api.packet.play.client.PacketEntityAction;
import com.thomas15v.packetlib.impl.util.EntityUtil;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by thomas15v on 1/04/15.
 */
@Mixin(C0BPacketEntityAction.class)
public class MixinPacketEntityAction implements PacketEntityAction {
    @Shadow
    private int entityID;
    @Shadow
    private C0BPacketEntityAction.Action action;
    @Shadow
    private int auxData;

    @Override
    public PlayPacketEvent getEvent(ConnectionUser player) {
        return new PacketEntityActionEvent(this, player);
    }

    @Override
    public State getState() {
        return State.Play;
    }

    @Override
    public PacketEntityAction.EntityAction getAction() {
        return EntityAction.valueOf(action.name());
    }

    @Override
    public void setAction(EntityAction action) {
        this.action = C0BPacketEntityAction.Action.valueOf(action.name());
    }

    @Override
    public Entity getEntity() {
        return (Entity) EntityUtil.getEntityObject(entityID);
    }

    @Override
    public Bound getBound() {
        return Bound.Client;
    }
}

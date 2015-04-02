package com.thomas15v.packetlib.api.packet.play.client;

import com.thomas15v.packetlib.api.packet.play.PlayPacket;
import org.spongepowered.api.entity.Entity;

/**
 * Created by thomas15v on 1/04/15.
 */
public abstract class PacketEntityAction extends PlayPacket {

    public enum EntityAction{
        START_SNEAKING,
        STOP_SNEAKING,
        STOP_SLEEPING,
        START_SPRINTING,
        STOP_SPRINTING,
        RIDING_JUMP,
        OPEN_INVENTORY;
    }

    public abstract EntityAction getAction();

    public abstract void setAction(EntityAction action);

    public abstract Entity getEntity();

}

package com.thomas15v.packetlib.api.packet.play.client;

import com.thomas15v.packetlib.api.packet.Packet;
import org.spongepowered.api.entity.Entity;

/**
 * Created by thomas15v on 1/04/15.
 */
public interface PacketEntityAction extends Packet {

    public enum EntityAction{
        START_SNEAKING,
        STOP_SNEAKING,
        STOP_SLEEPING,
        START_SPRINTING,
        STOP_SPRINTING,
        RIDING_JUMP,
        OPEN_INVENTORY;
    }

    EntityAction getAction();

    void setAction(EntityAction action);

    Entity getEntity();

}

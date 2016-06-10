package com.thomas15v.packetlib.api.data;

import net.minecraft.network.Packet;

import java.lang.reflect.Field;

public class PacketContainer {

    private Packet packet;

    public PacketContainer(Packet packet){
        this.packet = packet;
    }

    public void set(String fieldName, Object value) throws NoSuchFieldException {
        try {
            getField(fieldName).set(packet, value);
        } catch (IllegalAccessException ignored) {}
    }

    public <I> I get(String fieldName) throws NoSuchFieldException {
        try {
            return (I) getField(fieldName).get(packet);
        } catch (IllegalAccessException ignored) {
            return null;
        }

    }

    private Field getField(String fieldname) throws NoSuchFieldException {
        Field field = packet.getClass().getField(fieldname);
        field.setAccessible(true);
        return field;
    }
}

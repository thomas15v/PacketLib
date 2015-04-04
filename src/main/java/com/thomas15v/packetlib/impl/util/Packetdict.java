package com.thomas15v.packetlib.impl.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.thomas15v.packetlib.api.packet.Packet;
import com.thomas15v.packetlib.api.packet.play.client.PacketAnimation;
import com.thomas15v.packetlib.api.packet.play.client.PacketEntityAction;
import com.thomas15v.packetlib.api.packet.play.client.PacketInput;
import com.thomas15v.packetlib.api.packet.play.shared.PacketUpdateSign;
import com.thomas15v.packetlib.api.packet.play.server.PacketcollectItem;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S33PacketUpdateSign;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by thomas15v on 1/04/15.
 */
public class Packetdict {

    static private BiMap<Class, Class> clazzmap;

    static public Class<? extends net.minecraft.network.Packet> getNMSPacketFor(Class clazz){
        return clazzmap.get(clazz);
    }

    static public Class<? extends Packet> getAPIPacketFor(Class clazz){
        return clazzmap.inverse().get(clazz);
    }

    static {
        clazzmap = HashBiMap.create();
        clazzmap.put(PacketAnimation.class, C0APacketAnimation.class);
        clazzmap.put(PacketUpdateSign.class, S33PacketUpdateSign.class);
        clazzmap.put(PacketcollectItem.class, S0DPacketCollectItem.class);
        clazzmap.put(PacketEntityAction.class, C0BPacketEntityAction.class);
        clazzmap.put(PacketInput.class, C0CPacketInput.class);
    }

    public static Packet createFor(Class clazz, Object... params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class packetclazz = getNMSPacketFor(clazz);

        Class[] constructorparams = new Class[params.length];
        for (int i = 0; i < params.length; i++)
            constructorparams[i] = params[i].getClass();

        Constructor constructor = packetclazz.getConstructor(constructorparams);

        return (Packet) constructor.newInstance(params);
    }


}

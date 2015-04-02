package com.thomas15v.packetlib.impl.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.thomas15v.packetlib.api.packet.play.client.PacketAnimation;
import com.thomas15v.packetlib.api.packet.play.shared.PacketUpdateSign;
import com.thomas15v.packetlib.api.packet.play.server.PacketcollectItem;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S33PacketUpdateSign;

/**
 * Created by thomas15v on 1/04/15.
 */
public class Packetdict {

    static private BiMap<Class, Class> clazzmap;

    static public Class getNMSPacketFor(Class clazz){
        return clazzmap.get(clazz);
    }

    static public Class getAPIPacketFor(Class clazz){
        return clazzmap.inverse().get(clazz);
    }

    static {
        clazzmap = HashBiMap.create();
        clazzmap.put(PacketAnimation.class, C0APacketAnimation.class);
        clazzmap.put(PacketUpdateSign.class, S33PacketUpdateSign.class);
        clazzmap.put(PacketcollectItem.class, S0DPacketCollectItem.class);
    }


}

package com.thomas15v.packetlib.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.thomas15v.packetlib.api.data.PacketContainer;
import com.thomas15v.packetlib.api.service.PacketService;
import com.thomas15v.packetlib.api.service.PacketListener;

public class PacketLibService implements PacketService {

    private Multimap<Class, PacketListener> listenerMap = ArrayListMultimap.create();

    public <I extends PacketContainer> void registerPacketListener(Class<I> packetType, PacketListener<I> packetListener){
        this.listenerMap.put(packetType, packetListener);
    }

    public void callPacket(PacketContainer packetContainer){
        if (this.listenerMap.containsKey(packetContainer.getClass())){
            this.listenerMap.get(packetContainer.getClass()).forEach(packetListener -> packetListener.onPacket(packetContainer));
        }
    }
}

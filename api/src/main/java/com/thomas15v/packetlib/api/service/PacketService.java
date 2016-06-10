package com.thomas15v.packetlib.api.service;

import com.thomas15v.packetlib.api.data.PacketContainer;

public interface PacketService {

    <I extends PacketContainer> void registerPacketListener(Class<I> packetType, PacketListener<I> packetListener);

}

package com.thomas15v.packetlib.api.service;

import com.thomas15v.packetlib.api.data.PacketContainer;

public interface PacketListener<I extends PacketContainer> {

    void onPacket(I packet);

}

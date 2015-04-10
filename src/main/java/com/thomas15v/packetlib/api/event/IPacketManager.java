package com.thomas15v.packetlib.api.event;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.packet.Packet;

public interface IPacketManager {

    boolean post(Packet packet, ConnectionUser connectionUser);

    void register(IPacketTransformer listener) throws Exception;

    void unregister(IPacketTransformer listener);
}

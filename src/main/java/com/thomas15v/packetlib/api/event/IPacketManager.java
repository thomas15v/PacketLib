package com.thomas15v.packetlib.api.event;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.packet.Packet;

/**
 * Created by thomas15v on 2/04/15.
 */
public interface IPacketManager {

    boolean post(Packet packet, ConnectionUser connectionUser);

    void register(IPacketTransformer listener) throws Exception;

    void unregister(IPacketTransformer listener);
}

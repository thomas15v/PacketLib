package com.thomas15v.testPlugin;

import com.thomas15v.packetlib.api.event.IPacketTransformer;
import com.thomas15v.packetlib.api.event.PacketEvent;
import com.thomas15v.packetlib.api.packet.play.shared.PacketUpdateSign;

/**
 * Created by thomas15v on 3/04/15.
 */
public class PacketTrans implements IPacketTransformer<PacketUpdateSign> {

    @Override
    public void onPacket(PacketEvent<PacketUpdateSign> event) {
        System.out.println(event.getPacket());
    }

    @Override
    public Class[] forPackets() {
        return new Class[]{
                PacketUpdateSign.class
        };
    }
}

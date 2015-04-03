package com.thomas15v.packetlib.api.packet.play.shared;

import com.flowpowered.math.vector.Vector3i;
import com.thomas15v.packetlib.api.packet.Packet;
import org.spongepowered.api.text.Text;

/**
 * Created by thomas15v on 31/03/15.
 */
public interface PacketUpdateSign extends Packet {

    Text[] getText();

    void setText(Text[] text);

    Vector3i getLocation();
}

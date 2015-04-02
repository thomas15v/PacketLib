package com.thomas15v.packetlib.api.packet.play.shared;

import com.flowpowered.math.vector.Vector3i;
import com.flowpowered.math.vector.Vectori;
import com.thomas15v.packetlib.api.packet.play.PlayPacket;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/**
 * Created by thomas15v on 31/03/15.
 */
public interface PacketUpdateSign extends PlayPacket {

    Text[] getText();

    void setText(Text[] text);

    Vector3i getLocation();
}

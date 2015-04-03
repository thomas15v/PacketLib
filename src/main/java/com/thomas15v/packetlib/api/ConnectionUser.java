package com.thomas15v.packetlib.api;

import com.thomas15v.packetlib.api.packet.Packet;
import org.spongepowered.api.entity.player.Player;

public interface ConnectionUser extends Player {

    void sendPacket(Packet packet);
}

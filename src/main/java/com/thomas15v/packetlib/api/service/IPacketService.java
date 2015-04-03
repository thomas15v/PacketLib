package com.thomas15v.packetlib.api.service;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.PacketManager;
import com.thomas15v.packetlib.api.packet.Packet;
import org.spongepowered.api.entity.player.Player;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by thomas15v on 2/04/15.
 */
public interface IPacketService {

    PacketManager getPacketManager();

    Packet createPacketFor(Class Packet) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    ConnectionUser getUserFor(Player player);
}

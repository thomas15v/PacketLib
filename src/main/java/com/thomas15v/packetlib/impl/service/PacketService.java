package com.thomas15v.packetlib.impl.service;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.PacketManager;
import com.thomas15v.packetlib.api.packet.Packet;
import com.thomas15v.packetlib.api.service.IPacketService;
import com.thomas15v.packetlib.impl.event.PacketObserver;
import com.thomas15v.packetlib.impl.util.Packetdict;
import org.spongepowered.api.entity.player.Player;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by thomas15v on 2/04/15.
 */
public class PacketService implements IPacketService {

    PacketObserver packetObserver;

    public PacketService(){
        this.packetObserver = new PacketObserver();
    }

    @Override
    public PacketManager getPacketManager() {
        return packetObserver;
    }

    @Override
    public Packet createPacketFor(Class packet) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return Packetdict.createFor(packet);
    }

    @Override
    public ConnectionUser getUserFor(Player player) {
        return (ConnectionUser) player;
    }

}

package com.thomas15v.packetlib.impl.event;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.IPacketManager;
import com.thomas15v.packetlib.api.event.IPacketTransformer;
import com.thomas15v.packetlib.api.event.PacketEvent;
import com.thomas15v.packetlib.api.packet.Packet;
import com.thomas15v.packetlib.impl.util.Packetdict;
import org.spongepowered.api.util.event.Event;

/**
 * Created by thomas15v on 2/04/15.
 */
public class PacketObserver implements IPacketManager {

    private final Multimap<Class<? extends Packet>, IPacketTransformer> transformers = ArrayListMultimap.create();
    private final Object lock = new Object();

    @Override
    public boolean post(Packet packet, ConnectionUser connectionUser){
        Class<? extends Packet> clazz = Packetdict.getAPIPacketFor(packet.getClass());
        if (transformers.containsKey(clazz)){
            PacketEvent event = new PlayEvent(packet, connectionUser);
            for (IPacketTransformer listener : transformers.get(clazz)) {
                listener.onPacket(event);
            }
            //todo change this
            return ((PlayEvent) event).isCancelled();
        }
        return false;
    }

    @Override
    public void register(IPacketTransformer listener) throws Exception {
        for (Class clazz : listener.forPackets())
            transformers.put(clazz, listener);
    }


    @Override
    public void unregister(IPacketTransformer listener) {
        synchronized (lock) {
            for (Class clazz : listener.forPackets())
            transformers.remove(clazz, listener);
        }
    }

}

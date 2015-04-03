package com.thomas15v.packetlib.impl.event;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.PacketListener;
import com.thomas15v.packetlib.api.event.PacketManager;
import com.thomas15v.packetlib.api.packet.Packet;
import com.thomas15v.packetlib.impl.util.Packetdict;

/**
 * Created by thomas15v on 2/04/15.
 */
public class PacketObserver implements PacketManager {

    private final Multimap<Class<? extends Packet>, PacketListener> eventHandlers = ArrayListMultimap.create();
    private final Object lock = new Object();

    @Override
    public void post(Packet packet, ConnectionUser connectionUser){
        Class<? extends Packet> clazz = Packetdict.getAPIPacketFor(packet.getClass());
        if (eventHandlers.containsKey(clazz))
            synchronized (lock) {
                for (PacketListener listener : eventHandlers.get(clazz))
                    listener.onPacket(new PlayEvent(packet, connectionUser));
            }
    }

    @Override
    public void register(PacketListener listener) throws Exception {
        Class clazz = getPacketClass(listener);
        if (Packet.class == clazz)
            throw new Exception("You can't register a " + clazz + " Please use the generic option to provide a packet");
        synchronized (lock) {
            eventHandlers.put(clazz, listener);
        }
    }

    @Override
    public void unregister(PacketListener listener) {
        synchronized (lock) {
            eventHandlers.remove(getPacketClass(listener), listener);
        }
    }

    private Class getPacketClass(PacketListener listener){
       return listener.getClass().getMethods()[0].getParameterTypes()[0];
    }

}

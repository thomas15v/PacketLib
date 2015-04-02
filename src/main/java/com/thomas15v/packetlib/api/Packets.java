package com.thomas15v.packetlib.api;

import com.thomas15v.packetlib.api.packet.Packet;
import com.thomas15v.packetlib.impl.util.Packetdict;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by thomas15v on 1/04/15.
 */
public class Packets {

    public Packet createFor(Class clazz, Object... params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class packetclazz = Packetdict.getNMSPacketFor(clazz);

        Class[] constructorparams = new Class[params.length];
        for (int i = 0; i < params.length; i++)
            constructorparams[i] = params[i].getClass();

        Constructor constructor = packetclazz.getConstructor(constructorparams);

        return (Packet) constructor.newInstance(params);
    }

}

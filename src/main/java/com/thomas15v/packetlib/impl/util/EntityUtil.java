package com.thomas15v.packetlib.impl.util;

import net.minecraft.server.MinecraftServer;

/**
 * Created by thomas15v on 31/03/15.
 */
public class EntityUtil {

    @Deprecated
    public static Object getEntityObject(int id ){
        return MinecraftServer.getServer().getEntityWorld().getEntityByID(id);
    }



}

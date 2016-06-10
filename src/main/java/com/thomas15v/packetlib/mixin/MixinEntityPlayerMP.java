package com.thomas15v.packetlib.mixin;

import com.thomas15v.packetlib.api.data.ConnectionUser;
import com.thomas15v.packetlib.api.data.PacketContainer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by thomas15v on 1/04/15.
 */
@Mixin(EntityPlayerMP.class)
public abstract class MixinEntityPlayerMP implements ConnectionUser {

    @Shadow
    public NetHandlerPlayServer playerNetServerHandler;

    @Override
    public void sendPacket(PacketContainer packet) {
        playerNetServerHandler.sendPacket((net.minecraft.network.Packet) packet);
    }
}
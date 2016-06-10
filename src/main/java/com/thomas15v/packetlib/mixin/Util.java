package com.thomas15v.packetlib.mixin;

import com.thomas15v.packetlib.PacketLibPlugin;
import com.thomas15v.packetlib.api.data.PacketContainer;
import com.thomas15v.packetlib.api.packets.PacketRegistery;
import com.thomas15v.packetlib.service.PacketLibService;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class Util {

    public static void handlePacket(Packet packet, EnumPacketDirection direction, CallbackInfo ci){
        try {
            Class<? extends PacketContainer> containerClass = PacketRegistery.getClassForNMSPacket(packet.getClass());
            if (containerClass != null) {
                ((PacketLibService) PacketLibPlugin.getPacketLibService()).callPacket(containerClass.getConstructor(Packet.class).newInstance(packet));
            } else {
                System.out.println(packet.getClass());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

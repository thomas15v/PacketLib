package com.thomas15v.packetlib;

import com.thomas15v.packetlib.api.packets.play.client.C01PacketChatMessage;
import com.thomas15v.packetlib.api.service.PacketService;
import com.thomas15v.packetlib.service.PacketLibService;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id="packetlib", name = "PacketLib", version = "1.0")
public class PacketLibPlugin {

    private static PacketService packetLibService = new PacketLibService();

    public static PacketService getPacketLibService() {
        return packetLibService;
    }

    static {
        getPacketLibService().registerPacketListener(C01PacketChatMessage.class, packet -> {
            try {
                System.out.println(packet.getmessage());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
    }
}

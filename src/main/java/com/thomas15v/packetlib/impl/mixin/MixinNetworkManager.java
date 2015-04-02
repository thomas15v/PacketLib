package com.thomas15v.packetlib.impl.mixin;

import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.packet.play.PlayPacket;
import com.thomas15v.packetlib.impl.util.EventUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by thomas15v on 31/03/15.
 */
@Mixin(net.minecraft.network.NetworkManager.class)
public abstract class MixinNetworkManager {

    @Shadow
    private INetHandler packetListener;


    @Inject(method = "dispatchPacket", at = @At("HEAD"))
    public void onSendPacket(final Packet packet, final GenericFutureListener[] futureListeners, CallbackInfo ci){
        handlePacket(packet);
    }

    @Inject(method = "channelRead0", at = @At("HEAD"))
    public void onRecievePacket(ChannelHandlerContext context, Packet packet, CallbackInfo ci){
        handlePacket(packet);
    }

    private void handlePacket(Packet packet){
        if (packet instanceof PlayPacket) {
            ConnectionUser player = (ConnectionUser) ((NetHandlerPlayServer) packetListener).playerEntity;
            EventUtil.postEventFor((PlayPacket) packet, player);
        }
    }
}



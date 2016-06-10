package com.thomas15v.packetlib.mixin;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.thomas15v.packetlib.mixin.Util.handlePacket;

@Mixin(net.minecraft.network.NetworkManager.class)
public abstract class MixinNetworkManager {

    @Shadow
    private INetHandler packetListener;
    @Shadow
    private Channel channel;

    @Inject(method = "sendPacket", at = @At("HEAD"), remap = false, cancellable = true)
    public void onSendPacket(final Packet packet, final GenericFutureListener[] futureListeners, CallbackInfo ci){
        handlePacket(packet, EnumPacketDirection.CLIENTBOUND , ci);
    }

    @Inject(method = "channelRead0", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/Packet;processPacket(Lnet/minecraft/network/INetHandler;)V", shift = At.Shift.AFTER))
    public void onRecievePacket(ChannelHandlerContext context, Packet packet, CallbackInfo ci){
        handlePacket(packet, EnumPacketDirection.SERVERBOUND , ci);
    }


}


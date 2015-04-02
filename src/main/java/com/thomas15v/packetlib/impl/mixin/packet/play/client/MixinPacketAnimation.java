package com.thomas15v.packetlib.impl.mixin.packet.play.client;

import com.thomas15v.packetlib.api.packet.Bound;
import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.packet.State;
import com.thomas15v.packetlib.api.event.play.PlayPacketEvent;
import com.thomas15v.packetlib.api.event.play.client.PacketAnimationEvent;
import com.thomas15v.packetlib.api.packet.play.client.PacketAnimation;
import net.minecraft.network.play.client.C0APacketAnimation;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Created by thomas15v on 31/03/15.
 */
@Mixin(C0APacketAnimation.class)
public class MixinPacketAnimation implements PacketAnimation {

    @Override
    public State getState() {
        return State.Play;
    }

    @Override
    public Bound getBound() {
        return Bound.Client;
    }
}

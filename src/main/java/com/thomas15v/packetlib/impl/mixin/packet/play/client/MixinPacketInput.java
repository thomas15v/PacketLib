package com.thomas15v.packetlib.impl.mixin.packet.play.client;

import com.thomas15v.packetlib.api.packet.Bound;
import com.thomas15v.packetlib.api.packet.State;
import com.thomas15v.packetlib.api.packet.play.client.PacketInput;
import net.minecraft.network.play.client.C0CPacketInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by thomas15v on 3/04/15.
 */
@Mixin(C0CPacketInput.class)
public class MixinPacketInput implements PacketInput {

    @Shadow
    private float strafeSpeed;
    @Shadow
    private float forwardSpeed;
    @Shadow
    private boolean jumping;
    @Shadow
    private boolean sneaking;

    @Override
    public float getStrafeSpeed() {
        return strafeSpeed;
    }

    @Override
    public void setStrafeSpeed(float strafeSpeed) {
        this.strafeSpeed = strafeSpeed;
    }

    @Override
    public float getForwardSpeed() {
        return forwardSpeed;
    }

    @Override
    public void setForwardSpeed(float forwardSpeed) {
        this.forwardSpeed = forwardSpeed;
    }

    @Override
    public boolean getJumping() {
        return jumping;
    }

    @Override
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    @Override
    public boolean getSneaking() {
        return sneaking;
    }

    @Override
    public void setSneaking(boolean sneaking) {
        this.sneaking = sneaking;
    }

    @Override
    public State getState() {
        return State.Play;
    }

    @Override
    public Bound getBound() {
        return Bound.Client;
    }
}

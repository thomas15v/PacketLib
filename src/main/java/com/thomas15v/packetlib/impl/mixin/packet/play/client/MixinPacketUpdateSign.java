package com.thomas15v.packetlib.impl.mixin.packet.play.client;

import com.flowpowered.math.vector.Vector3i;
import com.thomas15v.packetlib.api.packet.Bound;
import com.thomas15v.packetlib.api.packet.State;
import com.thomas15v.packetlib.api.packet.play.shared.PacketUpdateSign;
import com.thomas15v.packetlib.impl.util.TextUtil;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import org.spongepowered.api.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.mod.util.VecHelper;

@Mixin(C12PacketUpdateSign.class)
public class MixinPacketUpdateSign implements PacketUpdateSign {

    @Shadow
    private BlockPos pos;

    @Shadow
    private IChatComponent[] lines;

    @Override
    public Text[] getText() {
        return TextUtil.toTextArray(lines);
    }

    @Override
    public void setText(Text[] text) {
        lines = TextUtil.toChatComponentArray(text);
    }

    @Override
    public Vector3i getLocation() {
        return VecHelper.toVector(this.pos).toInt();
    }

    @Override
    public Bound getBound() {
        return Bound.Client;
    }

    @Override
    public State getState() {
        return State.Play;
    }
}

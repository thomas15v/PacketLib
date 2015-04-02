package com.thomas15v.packetlib.impl.mixin.packet.play.server;

import com.flowpowered.math.vector.Vector3i;
import com.thomas15v.packetlib.api.packet.Bound;
import com.thomas15v.packetlib.api.ConnectionUser;
import com.thomas15v.packetlib.api.event.play.PlayPacketEvent;
import com.thomas15v.packetlib.api.event.play.shared.PacketUpdateSignEvent;
import com.thomas15v.packetlib.api.packet.State;
import com.thomas15v.packetlib.api.packet.play.shared.PacketUpdateSign;
import com.thomas15v.packetlib.impl.util.TextUtil;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.util.IChatComponent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.annotation.NonnullByDefault;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by thomas15v on 31/03/15.
 */
@Mixin(value = S33PacketUpdateSign.class)
public class MixinPacketUpdateSign implements PacketUpdateSign {

    @Shadow()
    private IChatComponent[] field_149349_d;

    @Override
    public Text[] getText() {
       return TextUtil.toTextArray(field_149349_d);
    }

    @Override
    public void setText(Text[] text) {
        field_149349_d = TextUtil.toChatComponentArray(text);
    }

    @Override
    public Vector3i getLocation() {
        return null;
    }

    @Override
    public Bound getBound() {
        return Bound.Server;
    }

    @Override
    public PlayPacketEvent getEvent(ConnectionUser player) {
        return new PacketUpdateSignEvent(this, player);
    }

    @Override
    public State getState() {
        return State.Play;
    }
}

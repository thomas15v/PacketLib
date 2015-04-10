package com.thomas15v.packetlib.impl.mixin.packet.play.server;

import com.thomas15v.packetlib.api.packet.Bound;
import com.thomas15v.packetlib.api.packet.State;
import com.thomas15v.packetlib.api.packet.play.shared.CloseWindowPacket;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by thomas15v on 5/04/15.
 */
@Mixin(S2EPacketCloseWindow.class)
public class MixinCloseWindowPacket implements CloseWindowPacket {

    @Shadow
    private int windowId;

    @Override
    public int getWindowId() {
        return windowId;
    }

    @Override
    public void setWindowId(int id) {
        windowId = windowId;
    }

    @Override
    public Inventory getInventory() {
        throw new UnsupportedOperationException();
    }


    @Override
    public State getState() {
        return State.Play;
    }

    @Override
    public Bound getBound() {
        return Bound.Server;
    }
}

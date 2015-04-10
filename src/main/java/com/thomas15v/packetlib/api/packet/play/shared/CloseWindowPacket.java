package com.thomas15v.packetlib.api.packet.play.shared;

import com.thomas15v.packetlib.api.packet.Packet;
import org.spongepowered.api.item.inventory.Inventory;

/**
 * Created by thomas15v on 4/04/15.
 */
public interface CloseWindowPacket extends Packet {

    int getWindowId();

    void setWindowId(int id);

    Inventory getInventory();

}

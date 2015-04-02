package com.thomas15v.packetlib.impl.util;

import net.minecraft.util.IChatComponent;
import org.spongepowered.api.text.Text;
import org.spongepowered.mod.text.SpongeChatComponent;
import org.spongepowered.mod.text.SpongeText;

public class TextUtil {

    public static Text[] toTextArray(IChatComponent[] input){
        Text[] texts = new Text[input.length];
        for (int i = 0; i < texts.length; i++)
            texts[i] = ((SpongeChatComponent) input[i]).toText();
        return texts;
    }

    public static IChatComponent[] toChatComponentArray(Text[] input){
        IChatComponent[] texts = new IChatComponent[4];
        for (int i = 0; i < texts.length; i++)
            texts[i] = ((SpongeText) input[i]).toComponent();
        return texts;
    }

}

package com.thomas15v.packetlib.codegenerator.methodgenerator;

import com.thomas15v.packetlib.codegenerator.asm.AsmType;

public class TextGenerator extends StaticHelperGenerator {

    public TextGenerator(AsmType type) {
        super(type, "org.spongepowered.common.text.SpongeTexts", "net.minecraft.util.IChatComponent", "toText", "toComponent");
    }

}

package com.thomas15v.packetlib.codegenerator.methodgenerator;

import com.thomas15v.packetlib.codegenerator.asm.AsmType;

public class Vec3Generator extends StaticHelperGenerator {

    public Vec3Generator(AsmType type) {
        super(type, "org.spongepowered.common.util.VecHelper", "net.minecraft.util.Vec3", "toVector", "toVector");
    }

}

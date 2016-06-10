package com.thomas15v.packetlib.codegenerator.methodgenerator;

import com.thomas15v.packetlib.codegenerator.asm.AsmType;

public class BlockPosGenerator extends StaticHelperGenerator {
    public BlockPosGenerator(AsmType type) {
        super(type, "org.spongepowered.common.util.VecHelper", "net.minecraft.util.BlockPos", "toVector", "toBlockPos" );
    }
}

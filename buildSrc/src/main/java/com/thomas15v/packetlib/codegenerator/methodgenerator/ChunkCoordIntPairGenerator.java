package com.thomas15v.packetlib.codegenerator.methodgenerator;

/*import com.flowpowered.math.vector.Vector3i;
import net.minecraft.world.ChunkCoordIntPair;
import org.spongepowered.common.util.VecHelper;*/

import com.thomas15v.packetlib.codegenerator.asm.AsmType;

public class ChunkCoordIntPairGenerator extends StaticHelperGenerator {
    public ChunkCoordIntPairGenerator(AsmType type) {
        super(type, "org.spongepowered.common.util.VecHelper", "net.minecraft.world.ChunkCoordIntPair", "toVector", "toChunkCoordIntPair");
    }
}

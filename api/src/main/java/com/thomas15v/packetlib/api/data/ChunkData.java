package com.thomas15v.packetlib.api.data;

import org.spongepowered.api.block.BlockState;

public interface ChunkData {

    BlockState getBlockState(int x, int y, int z);

    void setBlockState(int x, int y, int z, BlockState blockState);

}

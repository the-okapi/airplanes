package com.unlimitedstuffltd;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class JetEngineBlock extends RotatedPillarBlock {
    public JetEngineBlock(Properties properties) {
        super(properties);
    }

    protected Vec3i isPowered(Level level, BlockPos pos) {
        List<BlockPos> neighborBlocks = List.of(
                pos.offset(0, 1, 0),
                pos.offset(0, -1, 0),
                pos.offset(1, 0, 0),
                pos.offset(-1, 0, 0),
                pos.offset(0, 0, 1),
                pos.offset(0, 0, -1)
        );
        List<Vec3i> offsets = List.of(
                new Vec3i(0, -1, 0),
                new Vec3i(0, 1, 0),
                new Vec3i(-1, 0, 0),
                new Vec3i(1, 0, 0),
                new Vec3i(0, 0, -1),
                new Vec3i(0, 0, 1)
        );
        int i = 0;
        for (BlockPos blockPos : neighborBlocks) {
            if (level.getBlockState(blockPos).is(Blocks.REDSTONE_BLOCK)) {
                return offsets.get(i);
            }
            i++;
        }
        return null;
    }

    protected List<BlockPos> getNeighbouringBlockPos(BlockPos pos) {
        return List.of(
                pos.offset(0, 1, 0),
                pos.offset(0, -1, 0),
                pos.offset(1, 0, 0),
                pos.offset(-1, 0, 0),
                pos.offset(0, 0, 1),
                pos.offset(0, 0, -1)
        );
    }

    protected List<BlockPos> getAirplaneBlocks(Level level, BlockPos pos) {
        ArrayList<BlockPos> airplaneBlocks = new ArrayList<>();
        ArrayList<BlockPos> uncheckedAirplaneBlocks = new ArrayList<>(List.of(pos));

        while (!uncheckedAirplaneBlocks.isEmpty()) {
            for (BlockPos blockPos : getNeighbouringBlockPos(uncheckedAirplaneBlocks.getFirst())) {
                if (level.getBlockState(blockPos) == Airplanes.AIRPLANE_BLOCK.defaultBlockState()) {
                    airplaneBlocks.add(blockPos);
                    uncheckedAirplaneBlocks.add(blockPos);
                }
            }
            uncheckedAirplaneBlocks.removeFirst();
        }

        return airplaneBlocks;
    }

    @Override
    protected void neighborChanged(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!(level instanceof ServerLevel)) {
            return;
        }
        Vec3i offset = isPowered(level, pos);
        if (offset == null) {
            return;
        }
        List<BlockPos> airplaneBlocks = getAirplaneBlocks(level, pos);
        for (BlockPos airplaneBlock : airplaneBlocks) {
            level.setBlock(airplaneBlock, Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(airplaneBlock.offset(offset), Airplanes.JET_ENGINE.defaultBlockState(), 2);
        }
    }
}

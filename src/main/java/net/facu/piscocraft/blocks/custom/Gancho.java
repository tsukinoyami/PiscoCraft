package net.facu.piscocraft.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.facu.piscocraft.blocks.custom.entity.GanchoEntity;
import net.facu.piscocraft.blocks.custom.entity.PiscocraftEntityTypes;
import net.facu.piscocraft.items.ItemClass;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class Gancho extends BlockWithEntity {
    public static final EnumProperty<GanchoState> STATE = EnumProperty.of("state", GanchoState.class);
    public Gancho(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(STATE, GanchoState.VACIO));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(STATE);
    }

    public enum GanchoState implements StringIdentifiable {
        VACIO("vacio"),
        CON_ITEM("con_item"),
        SECO("seco");

        private final String name;
        GanchoState(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return this.name;
        }
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        if (!(world.getBlockEntity(pos) instanceof GanchoEntity blockEntity)) {
            return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
        }
        if (!player.getStackInHand(hand).isEmpty()) {
            if (player.getStackInHand(hand).isOf(ItemClass.MARIHUANA))
                if (blockEntity.getStack(0).isEmpty()) {
                    ItemStack singleItem = player.getStackInHand(hand).copy();
                    singleItem.setCount(1);
                    blockEntity.setStack(0, singleItem);
                    player.getStackInHand(hand).decrement(1);
                    world.setBlockState(pos, state.with(STATE, GanchoState.CON_ITEM));
            } else {
                player.sendMessage(Text.literal("Ya hay algo en el gancho!"), false);
            }
        } else {
            if (!blockEntity.getStack(0).isEmpty()) {
                player.setStackInHand(hand, blockEntity.getStack(0).copy());
                blockEntity.setStack(0, ItemStack.EMPTY);
                world.setBlockState(pos, state.with(STATE, GanchoState.VACIO));
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(Gancho::new);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GanchoEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockAbove = pos.up();
        return world.getBlockState(blockAbove).isSideSolidFullSquare(world, blockAbove, Direction.DOWN);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, wireOrientation, notify);
        System.out.println(world.getBlockState(pos.up()));
        if (world.getBlockState(pos.up()).isAir()) {
            world.breakBlock(pos, true);
        }
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, PiscocraftEntityTypes.GANCHO, GanchoEntity::tick);
    }
}

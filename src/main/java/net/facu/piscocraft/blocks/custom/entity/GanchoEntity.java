package net.facu.piscocraft.blocks.custom.entity;

import net.facu.piscocraft.blocks.custom.Gancho;
import net.facu.piscocraft.items.ItemClass;
import net.facu.piscocraft.utils.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GanchoEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private int dryingTime = 0;
    private static final int REQUIRED_TIME = 20 * 60;
    public GanchoEntity(BlockPos pos, BlockState state) {
        super(PiscocraftEntityTypes.GANCHO, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, GanchoEntity entity) {
        if (!world.isClient) {
            ItemStack currentItem = entity.getStack(0);
            if (!currentItem.isEmpty() && currentItem.isOf(ItemClass.MARIHUANA)) {
                entity.dryingTime++;
                if (entity.dryingTime >= REQUIRED_TIME) {
                    entity.setStack(0, new ItemStack(ItemClass.MARIHUANA_SECA));
                    entity.dryingTime = 0;
                    entity.markDirty();
                    ((ServerWorld) world).spawnParticles(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 10, 0.5, 0.5, 0.5, 0.1);
                    ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 10, 0.5, 0.5, 0.5, 0.1);
                    world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(pos, state.with(Gancho.STATE, Gancho.GanchoState.SECO));
                }
            } else {
                entity.dryingTime = 0;
            }
        }
    }
    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        super.readNbt(nbt, wrapperLookup);
        Inventories.readNbt(nbt, items, wrapperLookup);
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        Inventories.writeNbt(nbt, items, registries);
        super.writeNbt(nbt, registries);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
}

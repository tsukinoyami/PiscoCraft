package net.facu.piscocraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.facu.piscocraft.blocks.BlockClass;
import net.facu.piscocraft.items.ItemClass;
import net.facu.piscocraft.blocks.crops.CannabisCropBlock;
import net.facu.piscocraft.blocks.crops.VidCropBlock;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BlockLootTableProvider extends FabricBlockLootTableProvider {
    protected BlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BlockClass.VIDCROP, cropDrops(BlockClass.VIDCROP, ItemClass.UVA, ItemClass.VIDSEEDS, BlockStatePropertyLootCondition.builder(BlockClass.VIDCROP).properties(StatePredicate.Builder.create().exactMatch(VidCropBlock.AGE, 7))));
        addDrop(BlockClass.CANNABISCROP, cropDrops(BlockClass.CANNABISCROP, ItemClass.MARIHUANA, ItemClass.CANNABISSEEDS, BlockStatePropertyLootCondition.builder(BlockClass.CANNABISCROP).properties(StatePredicate.Builder.create().exactMatch(CannabisCropBlock.AGE, 7))));
        addDrop(BlockClass.GANCHO);


    }

}

package net.facu.piscocraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.facu.piscocraft.constructors.BlockClass;
import net.facu.piscocraft.constructors.ItemClass;
import net.facu.piscocraft.custom.VidCropBlock;
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
    }
}

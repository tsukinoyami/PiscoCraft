package net.facu.piscocraft.blocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.facu.piscocraft.Piscocraft;
import net.facu.piscocraft.blocks.custom.Gancho;
import net.facu.piscocraft.items.ItemClass;
import net.facu.piscocraft.blocks.crops.VidCropBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class BlockClass {
    protected static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Piscocraft.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Piscocraft.MOD_ID, name));
    }

    public static void initialize(){
        ItemGroupEvents.modifyEntriesEvent(ItemClass.PiscoCraftGroupKey).register((itemGroup) -> {
            itemGroup.add(BlockClass.GANCHO.asItem());
        });
    }

    public static final Block VIDCROP = register(
            "vid_crop",
            VidCropBlock::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP),
            false);

    public static final Block CANNABISCROP = register(
            "cannabis_crop",
            VidCropBlock::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP),
            false);

    public static final Gancho GANCHO = (Gancho) register(
            "gancho", Gancho::new, AbstractBlock.Settings.create()
                    .strength(0.2f, 10.0f)
                    .sounds(BlockSoundGroup.IRON)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .nonOpaque(),
            true
    );
}
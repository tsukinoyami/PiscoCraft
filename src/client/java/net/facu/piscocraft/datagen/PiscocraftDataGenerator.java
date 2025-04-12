package net.facu.piscocraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.facu.piscocraft.items.ItemClass;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

            public class PiscocraftDataGenerator implements DataGeneratorEntrypoint {

                @Override
                public void onInitializeDataGenerator(FabricDataGenerator generator) {
                    FabricDataGenerator.Pack pack = generator.createPack();

                    pack.addProvider(AdvancementsProvider::new);
                    pack.addProvider(BlockLootTableProvider::new);
                }

                static class AdvancementsProvider extends FabricAdvancementProvider {
                    protected AdvancementsProvider(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
                        super(dataGenerator, registryLookup);
                    }

                    @Override
                    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
                        AdvancementEntry rootAdvancement = Advancement.Builder.create()
                                .display(
                                        ItemClass.VIDSEEDS,
                                        Text.literal("Piscocraft"),
                                        Text.literal("Primeros pasos"),
                                        Identifier.ofVanilla("textures/gui/advancements/backgrounds/stone.png"),
                                        AdvancementFrame.TASK,
                                        true,
                                        true,
                                        false
                                )
                                .criterion("got_vid", InventoryChangedCriterion.Conditions.items(ItemClass.VIDSEEDS))
                                .build(consumer, "piscocraft" + "/root");
                    }
                }
            }

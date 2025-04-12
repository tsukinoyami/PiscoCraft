package net.facu.piscocraft;

import net.fabricmc.api.ModInitializer;
import net.facu.piscocraft.blocks.BlockClass;
import net.facu.piscocraft.blocks.custom.entity.PiscocraftEntityTypes;
import net.facu.piscocraft.items.ItemClass;

public class Piscocraft implements ModInitializer {
    public static final String MOD_ID = "piscocraft";

    @Override
    public void onInitialize() {
        ItemClass.initialize();
        BlockClass.initialize();
        PiscocraftEntityTypes.initialize();
}}

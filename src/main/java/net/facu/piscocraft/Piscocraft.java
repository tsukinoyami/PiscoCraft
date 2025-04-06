package net.facu.piscocraft;

import net.fabricmc.api.ModInitializer;
import net.facu.piscocraft.constructors.BlockClass;
import net.facu.piscocraft.constructors.ItemClass;

public class Piscocraft implements ModInitializer {
    public static final String MOD_ID = "piscocraft";

    @Override
    public void onInitialize() {
        ItemClass.initialize();
        BlockClass.initialize();
    }
}

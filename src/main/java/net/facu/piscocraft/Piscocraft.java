package net.facu.piscocraft;

import net.fabricmc.api.ModInitializer;

public class Piscocraft implements ModInitializer {
    public static final String MOD_ID = "piscocraft";

    @Override
    public void onInitialize() {
        ItemClass.initialize();
    }
}

package net.facu.piscocraft.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.facu.piscocraft.blocks.BlockClass;
import net.minecraft.client.render.RenderLayer;

public class PiscocraftClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockClass.CANNABISCROP, RenderLayer.getCutout());

    }
}

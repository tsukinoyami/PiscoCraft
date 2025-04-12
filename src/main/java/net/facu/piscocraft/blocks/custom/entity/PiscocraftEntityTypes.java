package net.facu.piscocraft.blocks.custom.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.facu.piscocraft.Piscocraft;
import net.facu.piscocraft.blocks.BlockClass;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PiscocraftEntityTypes {
    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Piscocraft.MOD_ID, path), blockEntityType);
    }

    public static final BlockEntityType<GanchoEntity> GANCHO = register(
            "gancho_disecador",
            FabricBlockEntityTypeBuilder.create(GanchoEntity::new, BlockClass.GANCHO).build()
    );
    public static void initialize() {
    }
}

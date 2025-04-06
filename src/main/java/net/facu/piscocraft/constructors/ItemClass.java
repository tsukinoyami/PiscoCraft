package net.facu.piscocraft.constructors;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.facu.piscocraft.Piscocraft;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ItemClass {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Piscocraft.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static final ConsumableComponent ALCOHOL_CONSUMABLE_COMPONENT = ConsumableComponents.food()
            .sound(SoundEvents.ENTITY_GENERIC_DRINK)
            .consumeEffect(new ApplyEffectsConsumeEffect
                    (new StatusEffectInstance(StatusEffects.NAUSEA, 30 * 20, 1), 0.8f
                    )).build();

    public static final ConsumableComponent LASERENA_CONSUMABLE_COMPONENT = ConsumableComponents.food()
            .sound(SoundEvents.ENTITY_GENERIC_DRINK)
            .consumeParticles(ParticleTypes.HAPPY_VILLAGER.shouldAlwaysSpawn())
            .consumeEffect(new ApplyEffectsConsumeEffect
                    (new StatusEffectInstance(StatusEffects.POISON, 7 * 20, 2), 0.3f
                    )).build();

    public static final FoodComponent JAGERMEISTER_COMPONENT = new FoodComponent.Builder()
            .alwaysEdible()
            .nutrition(6)
            .build();
    public static final FoodComponent UVA_COMPONENT = new FoodComponent.Builder()
            .alwaysEdible()
            .nutrition(3)
            .build();

    // item creation
    public static final Item JAGERMEISTER = register("jagermeister", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item MISTRAL = register("mistral", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item ALTOSDELCARMEN = register("altos_del_carmen", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item TRESERRES = register("tres_erres", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item BEEFEATER = register("beefeater", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item BLUELABEL = register("blue_label", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item CAMPANARIO = register("campanario", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item CAPEL = register("capel", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item GATO = register("gato", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item HORCONQUEMADO = register("horcon_quemado", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item LASERENA = register("la_serena", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, LASERENA_CONSUMABLE_COMPONENT));
    public static final Item CIENTOVEINTE = register("120", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item ABSOLUTVODKA = register("absolut_vodka", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item CASILLERODELDIABLO = register("casillero_del_diablo", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item CORONA = register("corona", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item CRISTAL = register("cristal", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item LEMONSTONES = register("lemon_stones", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item REDBULL = register("red_bull", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item TULA = register("tula", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item VODKABLACK = register("vodka_black", Item::new, new Item.Settings().food(JAGERMEISTER_COMPONENT, ALCOHOL_CONSUMABLE_COMPONENT));
    public static final Item VIDSEEDS = register("vid_seeds", settings -> new BlockItem(BlockClass.VIDCROP, settings), new Item.Settings().useItemPrefixedTranslationKey());
    public static final Item CANNABISSEEDS = register("cannabis_seeds", settings -> new BlockItem(BlockClass.CANNABISCROP, settings), new Item.Settings().useItemPrefixedTranslationKey());
    public static final Item UVA = register("uva", Item::new, new Item.Settings().food(UVA_COMPONENT));

    public static final RegistryKey<ItemGroup> PiscoCraftGroupKey = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Piscocraft.MOD_ID, "item_group"));


    public static final ItemGroup PiscoCraftGroup = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ItemClass.JAGERMEISTER))
            .displayName(Text.translatable("itemGroup.piscocraft"))
            .build();

    public static void initialize(){
        Registry.register(Registries.ITEM_GROUP, PiscoCraftGroupKey, PiscoCraftGroup);
        ItemGroupEvents.modifyEntriesEvent(PiscoCraftGroupKey).register(itemGroup -> {
            itemGroup.add(ItemClass.JAGERMEISTER);
            itemGroup.add(ItemClass.MISTRAL);
            itemGroup.add(ItemClass.ALTOSDELCARMEN);
            itemGroup.add(ItemClass.TRESERRES);
            itemGroup.add(ItemClass.BEEFEATER);
            itemGroup.add(ItemClass.BLUELABEL);
            itemGroup.add(ItemClass.CAMPANARIO);
            itemGroup.add(ItemClass.CAPEL);
            itemGroup.add(ItemClass.GATO);
            itemGroup.add(ItemClass.LASERENA);
            itemGroup.add(ItemClass.HORCONQUEMADO);
            itemGroup.add(ItemClass.CIENTOVEINTE);
            itemGroup.add(ItemClass.ABSOLUTVODKA);
            itemGroup.add(ItemClass.CASILLERODELDIABLO);
            itemGroup.add(ItemClass.CORONA);
            itemGroup.add(ItemClass.CRISTAL);
            itemGroup.add(ItemClass.LEMONSTONES);
            itemGroup.add(ItemClass.REDBULL);
            itemGroup.add(ItemClass.TULA);
            itemGroup.add(ItemClass.VODKABLACK);
            itemGroup.add(ItemClass.VIDSEEDS);
            itemGroup.add(ItemClass.CANNABISSEEDS);
        });
    }

}
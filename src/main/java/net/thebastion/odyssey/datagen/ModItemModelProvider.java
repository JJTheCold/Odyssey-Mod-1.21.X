package net.thebastion.odyssey.datagen;

import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.block.ModBlocks;
import net.thebastion.odyssey.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Odyssey.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //buttonItem(ModBlocks.BISMUTH_BUTTON, ModBlocks.BISMUTH_BLOCK);
        //fenceItem(ModBlocks.BISMUTH_FENCE, ModBlocks.BISMUTH_BLOCK);
        //wallItem(ModBlocks.BISMUTH_WALL, ModBlocks.BISMUTH_BLOCK);

        withExistingParent(ModItems.HAN_D_SPAWNER.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.BURIED_SPAWNER.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.BURIED_LESSER_SPAWNER.getId().getPath(), mcLoc("item/template_spawn_egg"));

        wallItem(ModBlocks.KRUXROCK_TILE_WALL, ModBlocks.KRUXROCK_TILE);
        wallItem(ModBlocks.KRUXROCK_BRICK_WALL, ModBlocks.KRUXROCK_BRICKS);
        wallItem(ModBlocks.POLISHED_KRUXROCK_WALL, ModBlocks.POLISHED_KRUXROCK);
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(Odyssey.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(Odyssey.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(Odyssey.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Odyssey.MODID,"item/" + item.getId().getPath()));
    }
}
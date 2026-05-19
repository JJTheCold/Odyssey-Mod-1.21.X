package net.thebastion.oblivion.block.custom;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class CobaltCrucibleMenu extends AbstractFurnaceMenu {
    public CobaltCrucibleMenu(int containerId, Inventory playerInventory) {
        super(MenuType.FURNACE, RecipeType.BLASTING, RecipeBookType.BLAST_FURNACE, containerId, playerInventory);
    }

    public CobaltCrucibleMenu(int containerId, Inventory playerInventory, Container furnaceContainer, ContainerData furnaceData) {
        super(MenuType.FURNACE, RecipeType.BLASTING, RecipeBookType.BLAST_FURNACE, containerId, playerInventory, furnaceContainer, furnaceData);
    }
}

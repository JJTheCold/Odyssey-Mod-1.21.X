package net.thebastion.oblivion.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CobaltCrucibleBlockEntity extends AbstractFurnaceBlockEntity {
    public static final int BURN_TIME_STANDARD = 100;
    public CobaltCrucibleBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityType.BLAST_FURNACE, pos, blockState, RecipeType.BLASTING);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.oblivion.cobalt-crucible");
    }

    @Override
    protected int getBurnDuration(ItemStack fuel) {
        return super.getBurnDuration(fuel);
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new CobaltCrucibleMenu(id, player, this, this.dataAccess);
    }
}

package net.thebastion.odyssey.entity.client.buried.lesser;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.layers.SkeletonClothingLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Bogged;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.client.buried.BuriedModel;
import net.thebastion.odyssey.entity.custom.Buried;
import net.thebastion.odyssey.entity.custom.BuriedLesser;
import net.thebastion.odyssey.util.ModModelLayers;

@OnlyIn(Dist.CLIENT)
public class BuriedLesserRenderer extends SkeletonRenderer<BuriedLesser> {
    private static final ResourceLocation BURIED_SKELETON_LOCATION = ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "textures/entity/buried/buried-skeleton.png");
    private static final ResourceLocation BURIED_OUTER_LAYER_LOCATION = ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "textures/entity/buried/buried-skeleton-outer.png");

    public BuriedLesserRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.BURIED_INNER_ARMOR, ModModelLayers.BURIED_OUTER_ARMOR, new BuriedLesserModel(context.bakeLayer(ModModelLayers.BURIED)));
        this.addLayer(new SkeletonClothingLayer<>(this, context.getModelSet(), ModModelLayers.BURIED_OUTER_LAYER, BURIED_OUTER_LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(BuriedLesser p_331017_) {
        return BURIED_SKELETON_LOCATION;
    }

    @Override
    public void render(BuriedLesser entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}

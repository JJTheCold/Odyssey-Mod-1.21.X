package net.thebastion.odyssey.entity.client.champion;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.custom.Champion;

public class ChampionRenderer extends MobRenderer<Champion, ChampionModel<Champion>> {
    public ChampionRenderer(EntityRendererProvider.Context context) {
        super(context, new ChampionModel<>(context.bakeLayer(ChampionModel.LAYER_LOCATION)), 2f);
        this.addLayer(new ChampionGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Champion entity) {
        return ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "textures/entity/champion/champion-texture.png");
    }

    @Override
    public void render(Champion entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}

package net.thebastion.odyssey.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.custom.HanD;

public class HanDRenderer extends MobRenderer<HanD, HanDModel<HanD>> {
    public HanDRenderer(EntityRendererProvider.Context context) {
        super(context, new HanDModel<>(context.bakeLayer(HanDModel.LAYER_LOCATION)), 2f);
        this.addLayer(new HanDGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(HanD entity) {
        return ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "textures/entity/han-d/han-d_texture.png");
    }

    @Override
    public void render(HanD entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}

package net.thebastion.odyssey.entity.client.buried;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.client.han_d.HanDGlowLayer;
import net.thebastion.odyssey.entity.client.han_d.HanDModel;
import net.thebastion.odyssey.entity.custom.Buried;
import net.thebastion.odyssey.entity.custom.HanD;

public class BuriedRenderer extends MobRenderer<Buried, BuriedModel<Buried>> {
    public BuriedRenderer(EntityRendererProvider.Context context) {
        super(context, new BuriedModel<>(context.bakeLayer(BuriedModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Buried entity) {
        return ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "textures/entity/buried/buried-warrior-texture.png");
    }

    @Override
    public void render(Buried entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}

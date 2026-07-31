package net.thebastion.odyssey.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.custom.RelicBolt;

public class RelicBoltRenderer extends EntityRenderer<RelicBolt> {
    private final RelicBoltModel<RelicBolt> model;

    private static final ResourceLocation GLOW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "textures/projectile/relic-bolt-glow.png");

    public RelicBoltRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new RelicBoltModel<>(context.bakeLayer(RelicBoltModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(RelicBolt entity) {
        return ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "textures/projectile/relic-bolt.png");
    }

    @Override
    public void render(RelicBolt entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        // 1. Calculate and apply your existing projectile rotations
        Vec3 motion = entity.getDeltaMovement().add(entity.getDeltaMovement().subtract(entity.getDeltaMovement()).scale(partialTick));
        poseStack.mulPose(Axis.YP.rotationDegrees(entity.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(-entity.getXRot() + 180));

        // 2. Render the base projectile texture (affected by normal shadows/lighting)
        VertexConsumer baseConsumer = bufferSource.getBuffer(this.model.renderType(this.getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, baseConsumer, packedLight, OverlayTexture.NO_OVERLAY);

        // 3. Render your emissive glow overlay (uses RenderType.eyes and full-bright lighting value)
        VertexConsumer glowConsumer = bufferSource.getBuffer(RenderType.eyes(GLOW_TEXTURE));
        this.model.renderToBuffer(poseStack, glowConsumer, 15728880, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

package net.thebastion.odyssey.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.custom.ScytheProjectile;
import net.thebastion.odyssey.item.ModItems;

public class ScytheProjectileRenderer extends EntityRenderer<ScytheProjectile> {

    public ScytheProjectileRenderer(Context context) {
        super(context);
    }

    @Override
    public void render(ScytheProjectile entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        var item = ModItems.GUIDED_SCYTHE;
        poseStack.pushPose();
        Vec3 motion = entity.getDeltaMovement().add(entity.getDeltaMovement().subtract(entity.getDeltaMovement()).scale(partialTick));
        float xRot = -((float) (Mth.atan2(motion.horizontalDistance(), motion.x) * (double) (180F / (float) Math.PI)) - 90.0F);
        float yRot = -((float) (Mth.atan2(motion.z, motion.x) * (double) (180F / (float) Math.PI)) + 90.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(80));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot - (entity.tickCount + partialTick) * 36));
        Minecraft.getInstance().getItemRenderer().renderStatic(item.toStack(), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, entity.level, 0);
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(ScytheProjectile entity) {
        return ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "textures/item/guided-scythe.png");
    }
}
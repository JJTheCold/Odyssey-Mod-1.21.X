package net.thebastion.odyssey.accessory;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.util.DataAttachmentRegistry;
import net.thebastion.odyssey.util.IArmorCapeProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class CloakRenderer implements ICurioRenderer {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "textures/curios/cloak-texture.png");
    private final CloakModel<LivingEntity> model;

    public CloakRenderer(ModelPart part) {
        this.model = new CloakModel<>(part);
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(
            ItemStack stack, SlotContext slotContext, PoseStack matrixStack,
            RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer,
            int light, float limbSwing, float limbSwingAmount, float partialTicks,
            float ageInTicks, float netHeadYaw, float headPitch ) {

        LivingEntity entity = slotContext.entity();
        matrixStack.pushPose();

        ICurioRenderer.followBodyRotations(entity);

        matrixStack.translate(0.0D, -1.5D, 0);
        matrixStack.scale(2, 2, 2);

        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(
                renderTypeBuffer, RenderType.entityCutoutNoCull(TEXTURE), stack.hasFoil());

        this.model.renderToBuffer(matrixStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY);

        matrixStack.popPose();
    }
}
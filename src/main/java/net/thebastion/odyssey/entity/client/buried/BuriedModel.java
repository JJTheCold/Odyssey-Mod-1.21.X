package net.thebastion.odyssey.entity.client.buried;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.client.han_d.HanDAnimations;
import net.thebastion.odyssey.entity.custom.Buried;
import net.thebastion.odyssey.entity.custom.HanD;

public class BuriedModel<T extends Buried> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "buried"), "buried");
	private final ModelPart Burried;
	private final ModelPart body;
	private final ModelPart head;

	public BuriedModel(ModelPart root) {
		this.Burried = root.getChild("Burried");
		this.body = this.Burried.getChild("body");
		this.head = this.body.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Burried = partdefinition.addOrReplaceChild("Burried", CubeListBuilder.create(), PartPose.offset(0.0F, 12.5F, 0.0F));

		PartDefinition body = Burried.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 55).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(32, 55).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 71).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 71).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-4.0F, -11.0F, 0.0F, -0.1317F, 0.1412F, 0.0694F));

		PartDefinition right_arm_r1 = right_arm.addOrReplaceChild("right_arm_r1", CubeListBuilder.create().texOffs(56, 55).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.1F))
				.texOffs(58, 39).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 2.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition right_arm_r2 = right_arm.addOrReplaceChild("right_arm_r2", CubeListBuilder.create().texOffs(60, 16).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-1.0F, 2.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition weapon = right_arm.addOrReplaceChild("weapon", CubeListBuilder.create().texOffs(0, 28).addBox(0.0F, -5.0F, -24.0F, 0.0F, 10.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 10.0F, 0.0F, 0.0F, -0.2182F, 1.5708F));

		PartDefinition cube_r1 = weapon.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 28).addBox(0.0F, -5.0F, -6.5F, 0.0F, 10.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -17.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r2 = weapon.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 51).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 32.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, -11.0F, 0.0F, -0.1304F, -0.1525F, -0.1561F));

		PartDefinition left_arm_r1 = left_arm.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(32, 71).mirror().addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 5.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_arm_r2 = left_arm.addOrReplaceChild("left_arm_r2", CubeListBuilder.create().texOffs(60, 16).mirror().addBox(-2.0F, -4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(1.0F, 2.0F, 0.0F, 0.0F, -3.1416F, 0.0F));

		PartDefinition shield = left_arm.addOrReplaceChild("shield", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -14.0F, -3.5F, 14.0F, 28.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 8.5F, 0.0F, -1.5708F, 0.0F, 1.5708F));

		PartDefinition handle_r1 = shield.addOrReplaceChild("handle_r1", CubeListBuilder.create().texOffs(8, 71).addBox(-1.0F, -3.0F, -1.25F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(28, 0).addBox(-6.0F, -11.0F, -2.25F, 12.0F, 22.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.75F, 0.0F, 0.0F, -3.1416F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 23).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(26, 39).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition headwear = head.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(54, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = Burried.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 71).mirror().addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 0.0F, 0.1F, -0.2182F, 0.0F, 0.0F));

		PartDefinition right_leg = Burried.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 71).addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.1F, 0.2182F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	private void applyHeadRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -120f, 120f);
		headPitch = Mth.clamp(headPitch, -60f, 100);

		this.head.yRot = headYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch *  ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		Burried.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return Burried;
	}

	@Override
	public void setupAnim(Buried entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch);
        if (entity.hasClientTarget()) {
			this.animateWalk(BuriedAnimations.GUARD_WALK, limbSwing, limbSwingAmount, 2f, 1.5f);
			this.animate(entity.idleAnimationState, BuriedAnimations.GUARD_IDLE, ageInTicks, 1f);
		} else {
			this.animateWalk(BuriedAnimations.WALK, limbSwing, limbSwingAmount, 1.5f, 1.5f);
			this.animate(entity.idleAnimationState, BuriedAnimations.IDLE, ageInTicks, 1f);
		}
		this.animate(entity.attackAnimationState, BuriedAnimations.BASIC_ATTACK, ageInTicks);
	}
}
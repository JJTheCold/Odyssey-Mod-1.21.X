package net.thebastion.odyssey.accessory;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.thebastion.odyssey.Odyssey;

public class CloakModel<T extends LivingEntity> extends HumanoidModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "body"), "main");
	public final ModelPart body;

	public CloakModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
    }

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 1.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(3.15F, 0.0F, 3.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 75).mirror().addBox(-12.5F, -0.5F, -7.5F, 5.0F, 4.0F, 8.0F, new CubeDeformation(0.2F)).mirror(false)
				.texOffs(46, 46).mirror().addBox(-12.0F, 0.0F, -7.0F, 12.0F, 22.0F, 7.0F, new CubeDeformation(0.2F)).mirror(false)
				.texOffs(46, 17).mirror().addBox(-12.0F, 0.0F, -7.0F, 12.0F, 22.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0867F, -0.0108F, 0.1422F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(46, 75).addBox(7.5F, -0.5F, -7.5F, 5.0F, 4.0F, 8.0F, new CubeDeformation(0.2F))
				.texOffs(46, 46).addBox(0.0F, 0.0F, -7.0F, 12.0F, 22.0F, 7.0F, new CubeDeformation(0.2F))
				.texOffs(46, 17).addBox(0.0F, 0.0F, -7.0F, 12.0F, 22.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, 0.0F, 0.0F, 0.0867F, 0.0108F, -0.1422F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T pEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.body);
	}
}
package net.thebastion.odyssey.entity.client.champion;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.client.han_d.HanDAnimations;
import net.thebastion.odyssey.entity.custom.Champion;
import net.thebastion.odyssey.entity.custom.HanD;

public class ChampionModel<T extends Champion> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "champion"), "main");
	private final ModelPart champion;
	private final ModelPart lower;
	private final ModelPart legright;
	private final ModelPart legright_alloy_joint;
	private final ModelPart legright_lower;
	private final ModelPart legleft;
	private final ModelPart legleft_alloy_joint;
	private final ModelPart legleft_lower;
	private final ModelPart upper;
	private final ModelPart whaist;
	private final ModelPart chest;
	private final ModelPart elytra;
	private final ModelPart wingl;
	private final ModelPart winglbottom;
	private final ModelPart wingr;
	private final ModelPart wingrbottom;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart armR;
	private final ModelPart armRlower;
	private final ModelPart handR;
	private final ModelPart armL;
	private final ModelPart armLlower;
	private final ModelPart handL;

	public ChampionModel(ModelPart root) {
		this.champion = root.getChild("champion");
		this.lower = this.champion.getChild("lower");
		this.legright = this.lower.getChild("legright");
		this.legright_alloy_joint = this.legright.getChild("legright_alloy_joint");
		this.legright_lower = this.legright_alloy_joint.getChild("legright_lower");
		this.legleft = this.lower.getChild("legleft");
		this.legleft_alloy_joint = this.legleft.getChild("legleft_alloy_joint");
		this.legleft_lower = this.legleft_alloy_joint.getChild("legleft_lower");
		this.upper = this.champion.getChild("upper");
		this.whaist = this.upper.getChild("whaist");
		this.chest = this.whaist.getChild("chest");
		this.elytra = this.chest.getChild("elytra");
		this.wingl = this.elytra.getChild("wingl");
		this.winglbottom = this.wingl.getChild("winglbottom");
		this.wingr = this.elytra.getChild("wingr");
		this.wingrbottom = this.wingr.getChild("wingrbottom");
		this.head = this.chest.getChild("head");
		this.arms = this.chest.getChild("arms");
		this.armR = this.arms.getChild("armR");
		this.armRlower = this.armR.getChild("armRlower");
		this.handR = this.armRlower.getChild("handR");
		this.armL = this.arms.getChild("armL");
		this.armLlower = this.armL.getChild("armLlower");
		this.handL = this.armLlower.getChild("handL");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition champion = partdefinition.addOrReplaceChild("champion", CubeListBuilder.create(), PartPose.offset(4.0F, -15.0F, 0.0F));

		PartDefinition lower = champion.addOrReplaceChild("lower", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legright = lower.addOrReplaceChild("legright", CubeListBuilder.create().texOffs(0, 122).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 19.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition cube_r1 = legright.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 102).addBox(-6.5F, -2.0F, -7.0F, 5.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.0F, 0.0F, -0.039F, 0.0754F, 0.6314F));

		PartDefinition cube_r2 = legright.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -2.0F, -6.0F, 8.0F, 34.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.0F, 0.0F, -0.0714F, 0.0458F, 0.1076F));

		PartDefinition cube_r3 = legright.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(46, 129).addBox(-3.0F, -6.8F, -2.0F, 6.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 10.5F, -2.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition legright_alloy_joint = legright.addOrReplaceChild("legright_alloy_joint", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 1.0F));

		PartDefinition cube_r4 = legright_alloy_joint.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(80, 53).mirror().addBox(-7.0F, -6.0F, -5.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.05F)).mirror(false), PartPose.offsetAndRotation(2.0F, 1.0F, -1.0F, 0.0872F, -0.0038F, 0.0435F));

		PartDefinition legright_lower = legright_alloy_joint.addOrReplaceChild("legright_lower", CubeListBuilder.create().texOffs(100, 128).addBox(-2.0F, -1.0F, -3.0F, 5.0F, 20.0F, 6.0F, new CubeDeformation(-0.05F)), PartPose.offset(0.0F, 1.0F, -1.0F));

		PartDefinition cube_r5 = legright_lower.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(24, 129).addBox(-6.5F, -1.0F, -4.0F, 7.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, -0.1739F, 0.0151F, 0.0859F));

		PartDefinition legleft = lower.addOrReplaceChild("legleft", CubeListBuilder.create().texOffs(0, 122).mirror().addBox(-3.0F, 0.0F, -3.0F, 6.0F, 19.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r6 = legleft.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 102).mirror().addBox(1.5F, -2.0F, -7.0F, 5.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, -1.0F, 0.0F, -0.039F, -0.0754F, -0.6314F));

		PartDefinition cube_r7 = legleft.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, -2.0F, -6.0F, 8.0F, 34.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, -1.0F, 0.0F, -0.0714F, -0.0458F, -0.1076F));

		PartDefinition cube_r8 = legleft.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(46, 129).mirror().addBox(-3.0F, -6.8F, -2.0F, 6.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 10.5F, -2.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition legleft_alloy_joint = legleft.addOrReplaceChild("legleft_alloy_joint", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 1.0F));

		PartDefinition cube_r9 = legleft_alloy_joint.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(80, 53).addBox(-1.0F, -6.0F, -5.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(-2.0F, 1.0F, -1.0F, 0.0872F, 0.0038F, -0.0435F));

		PartDefinition legleft_lower = legleft_alloy_joint.addOrReplaceChild("legleft_lower", CubeListBuilder.create().texOffs(100, 128).mirror().addBox(-3.0F, -1.0F, -3.0F, 5.0F, 20.0F, 6.0F, new CubeDeformation(-0.05F)).mirror(false), PartPose.offset(0.0F, 1.0F, -1.0F));

		PartDefinition cube_r10 = legleft_lower.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(24, 129).mirror().addBox(-0.5F, -1.0F, -4.0F, 7.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, -0.1739F, -0.0151F, -0.0859F));

		PartDefinition upper = champion.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(28, 86).addBox(-5.6996F, -4.0535F, -4.8912F, 12.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.3004F, -0.9465F, -0.1088F));

		PartDefinition whaist = upper.addOrReplaceChild("whaist", CubeListBuilder.create().texOffs(110, 89).addBox(-5.0F, -6.4034F, -1.7663F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.3004F, -3.6501F, -2.1249F));

		PartDefinition chest = whaist.addOrReplaceChild("chest", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -5.4034F, 3.2337F, -0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r11 = chest.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(122, 128).addBox(-2.0F, -12.0458F, -4.2693F, 4.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6063F, -1.1138F, 0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r12 = chest.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(28, 66).addBox(-8.0F, -11.7857F, -1.9461F, 16.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8063F, -1.1138F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r13 = chest.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(80, 30).addBox(-7.7313F, -11.7862F, -6.2332F, 10.0F, 13.0F, 10.0F, new CubeDeformation(0.5F))
		.texOffs(78, 66).addBox(-7.7313F, -11.7862F, -6.2332F, 10.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6063F, -1.1138F, 0.1855F, 0.3435F, 0.0631F));

		PartDefinition cube_r14 = chest.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(78, 66).mirror().addBox(-2.2687F, -11.7862F, -6.2332F, 10.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.6063F, -1.1138F, 0.1855F, -0.3435F, -0.0631F));

		PartDefinition elytra = chest.addOrReplaceChild("elytra", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -8.9782F, 5.5005F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r15 = elytra.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(66, 130).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -0.3031F, 0.483F, 0.1872F, -0.1841F, 0.7681F));

		PartDefinition wingl = elytra.addOrReplaceChild("wingl", CubeListBuilder.create(), PartPose.offsetAndRotation(0.9848F, -0.1737F, 0.0F, -0.0378F, 0.0218F, -0.0004F));

		PartDefinition cube_r16 = wingl.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(0.7358F, -6.6172F, 6.5342F, 12.0F, 43.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.9848F, 9.7799F, -7.1138F, 0.2588F, 0.0268F, 0.0028F));

		PartDefinition cube_r17 = wingl.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 91).mirror().addBox(0.9848F, -9.6055F, 8.9026F, 11.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(66, 130).mirror().addBox(8.9848F, -10.6055F, 7.4026F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.9848F, 9.7799F, -7.1138F, 0.2618F, 0.0F, 0.0F));

		PartDefinition winglbottom = wingl.addOrReplaceChild("winglbottom", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0527F, 2.9666F, -0.2404F, -0.0391F, 0.0875F, -0.2965F));

		PartDefinition cube_r18 = winglbottom.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 46).mirror().addBox(-2.7291F, -7.0651F, 7.0899F, 11.0F, 42.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0375F, 6.8133F, -6.8734F, 0.2455F, -0.0533F, 0.2981F));

		PartDefinition wingr = elytra.addOrReplaceChild("wingr", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.9848F, -0.1737F, 0.0F, -0.0378F, -0.0218F, 0.0004F));

		PartDefinition cube_r19 = wingr.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(40, 0).addBox(-12.7357F, -6.6172F, 6.5342F, 12.0F, 43.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9848F, 9.7799F, -7.1138F, 0.2588F, -0.0268F, -0.0028F));

		PartDefinition cube_r20 = wingr.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(66, 130).addBox(-14.9848F, -10.6055F, 7.4026F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9848F, 9.7799F, -7.1138F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r21 = wingr.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 91).addBox(-5.5F, -2.0F, -1.0F, 11.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -0.1294F, 0.483F, 0.2618F, 0.0F, 0.0F));

		PartDefinition wingrbottom = wingr.addOrReplaceChild("wingrbottom", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0527F, 2.9666F, -0.2404F, -0.0391F, -0.0875F, 0.2965F));

		PartDefinition cube_r22 = wingrbottom.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 46).addBox(-8.2709F, -7.0651F, 7.0899F, 11.0F, 42.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0375F, 6.8133F, -6.8734F, 0.2455F, 0.0533F, -0.2981F));

		PartDefinition head = chest.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.5966F, -4.2337F, 0.1731F, 0.0139F, 0.0084F));

		PartDefinition cube_r23 = head.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(106, 53).addBox(-1.8007F, -18.7896F, -12.357F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.2028F, 3.1199F, 2.4414F, -0.5724F, 2.5703F));

		PartDefinition cube_r24 = head.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(134, 105).mirror().addBox(-10.7896F, -1.1993F, -12.357F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 12.2028F, 3.1199F, -2.4414F, -0.5724F, 0.5713F));

		PartDefinition cube_r25 = head.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(118, 74).addBox(-2.5696F, -17.6147F, -5.4194F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(68, 110).addBox(-2.5696F, -23.6147F, -5.4194F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 12.2028F, 3.1199F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r26 = head.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(106, 15).addBox(-23.8007F, -11.7896F, -12.357F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.2028F, 3.1199F, 2.4414F, -0.5724F, 2.5703F));

		PartDefinition cube_r27 = head.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(86, 130).addBox(-1.2104F, -23.8007F, -12.357F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.2028F, 3.1199F, 2.4414F, 0.5724F, -2.5703F));

		PartDefinition cube_r28 = head.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(134, 105).addBox(7.8007F, -1.2104F, -12.357F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.2028F, 3.1199F, -2.4414F, 0.5724F, -0.5713F));

		PartDefinition arms = chest.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.3004F, -8.9465F, -1.1088F, 0.1309F, 0.0F, 0.0F));

		PartDefinition armR = arms.addOrReplaceChild("armR", CubeListBuilder.create().texOffs(38, 102).addBox(-7.6609F, -1.9832F, -3.586F, 8.0F, 20.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(72, 89).addBox(-11.6609F, -5.9832F, -5.586F, 8.0F, 10.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(72, 0).addBox(-10.6609F, -3.9832F, -4.586F, 8.0F, 21.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(106, 0).addBox(-11.6609F, 15.0168F, -5.586F, 10.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8207F, 0.6426F, -0.5031F, -0.0786F, 0.2396F, 0.1483F));

		PartDefinition cube_r29 = armR.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(28, 47).addBox(-24.0905F, -2.8343F, -7.9797F, 11.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.1515F, 9.2417F, 0.3937F, 0.0F, 0.0F, 0.2618F));

		PartDefinition armRlower = armR.addOrReplaceChild("armRlower", CubeListBuilder.create().texOffs(120, 15).addBox(-6.5F, -2.5F, -4.25F, 7.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(118, 53).addBox(-3.5F, -0.5F, -3.5F, 8.0F, 14.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1609F, 18.5168F, -0.086F, -0.0134F, -0.0336F, -0.1631F));

		PartDefinition cube_r30 = armRlower.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(100, 110).addBox(-23.9555F, 12.0988F, -4.9797F, 8.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.3124F, -9.2751F, 0.4797F, 0.0F, 0.0F, -0.2182F));

		PartDefinition handR = armRlower.addOrReplaceChild("handR", CubeListBuilder.create().texOffs(120, 35).addBox(-3.4763F, 0.7164F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9763F, 12.7836F, 0.0F, 0.0F, -0.0873F, -0.2182F));

		PartDefinition armL = arms.addOrReplaceChild("armL", CubeListBuilder.create().texOffs(72, 0).mirror().addBox(2.6609F, -3.9832F, -4.586F, 8.0F, 21.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(72, 89).mirror().addBox(3.6609F, -5.9832F, -5.586F, 8.0F, 10.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(38, 102).mirror().addBox(-0.3391F, -1.9832F, -3.586F, 8.0F, 20.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(106, 0).mirror().addBox(1.6609F, 15.0168F, -5.586F, 10.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(17.4214F, 0.6426F, -0.5031F, -0.0786F, -0.2396F, -0.1483F));

		PartDefinition cube_r31 = armL.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(28, 47).mirror().addBox(13.0905F, -2.8343F, -7.9797F, 11.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-9.1515F, 9.2417F, 0.3937F, 0.0F, 0.0F, -0.2618F));

		PartDefinition armLlower = armL.addOrReplaceChild("armLlower", CubeListBuilder.create().texOffs(120, 15).mirror().addBox(-0.5F, -2.5F, -4.25F, 7.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(118, 53).mirror().addBox(-4.5F, -0.5F, -3.5F, 8.0F, 14.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.1609F, 18.5168F, -0.086F, -0.0134F, 0.0336F, 0.1631F));

		PartDefinition cube_r32 = armLlower.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(100, 110).mirror().addBox(15.9555F, 12.0988F, -4.9797F, 8.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-13.3124F, -9.2751F, 0.4797F, 0.0F, 0.0F, 0.2182F));

		PartDefinition handL = armLlower.addOrReplaceChild("handL", CubeListBuilder.create().texOffs(120, 35).mirror().addBox(-2.5237F, 0.7164F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.9763F, 12.7836F, 0.0F, 0.0F, 0.0873F, 0.2182F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	private void applyHeadRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -800f, 800f);
		headPitch = Mth.clamp(headPitch, -60f, 100);

		this.head.yRot = headYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch *  ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		champion.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	public ModelPart root() {
		return champion;
	}

	@Override
	public void setupAnim(Champion entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch);
		boolean walkState = entity.isSprinting();
		this.animateWalk(ChampionAnimations.WALK, limbSwing, limbSwingAmount, 1f, 1f);
		if (walkState) {
			this.animateWalk(ChampionAnimations.WALK, limbSwing, limbSwingAmount, 1f, 1f);
		} else if (!walkState) {
			this.animate(entity.runAnimationState, ChampionAnimations.RUN, ageInTicks);
		}
		int T = entity.getAttackAnimationTick();
		if (T > 0) {
			this.animate(entity.attackAnimationState, ChampionAnimations.PUNCH, ageInTicks, 1f);
		}
		this.animate(entity.attackAnimationState, ChampionAnimations.PUNCH, ageInTicks, 1f);
	}
}
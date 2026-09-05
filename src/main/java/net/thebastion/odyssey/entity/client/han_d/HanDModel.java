package net.thebastion.odyssey.entity.client.han_d;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.custom.HanD;

public class HanDModel<T extends HanD> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "han_d"), "main");
    private final ModelPart han_d;
    private final ModelPart upper;
    private final ModelPart head;

    public HanDModel(ModelPart root) {
        this.han_d = root.getChild("han_d");
        this.upper = this.han_d.getChild("upper");
        this.head = this.upper.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition han_d = partdefinition.addOrReplaceChild("han_d", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0152F, 24.6F, -3.3264F, 0.0F, 3.1416F, 0.0F));

        PartDefinition upper = han_d.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(100, 107).addBox(-4.0F, -3.4F, 1.5F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(58, 24).addBox(-7.5F, -3.9F, -3.5F, 15.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(34, 81).addBox(-4.0F, -7.4F, -5.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(72, 97).addBox(-3.5F, -10.9F, -3.5F, 7.0F, 3.0F, 7.0F, new CubeDeformation(-0.3F))
                .texOffs(102, 87).addBox(-2.5F, -3.9F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-10.0F, -9.4F, -4.5F, 20.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0152F, -32.6F, -4.6737F));

        PartDefinition cube_r1 = upper.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 110).mirror().addBox(8.0F, -35.0F, -7.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-26.9307F, 25.0969F, 1.8682F, -0.1279F, 0.05F, 0.121F));

        PartDefinition cube_r2 = upper.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 110).addBox(-15.0F, -35.0F, -7.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(26.9307F, 25.0969F, 1.8682F, -0.1279F, -0.05F, -0.121F));

        PartDefinition bone11 = upper.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(72, 107).mirror().addBox(-3.0257F, -2.7782F, -2.3986F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 57).mirror().addBox(-5.0257F, -6.7782F, -7.3986F, 2.0F, 15.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 116).mirror().addBox(-3.0257F, 5.2218F, -1.3986F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(106, 55).mirror().addBox(-6.0257F, -2.7782F, -3.3986F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-12.0F, -7.0F, 0.0F, -0.1279F, 0.05F, 0.121F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(88, 107).mirror().addBox(-0.75F, 1.95F, -3.0F, 3.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(28, 93).mirror().addBox(-2.0F, 0.95F, -4.0F, 4.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(100, 112).mirror().addBox(-2.0F, 15.95F, -4.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(-1.5257F, 14.2718F, 1.6014F, 0.5672F, 0.0F, 0.0F));

        PartDefinition cube_r3 = bone12.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(72, 120).mirror().addBox(-3.0F, -1.5F, 1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(72, 120).mirror().addBox(-3.0F, -1.5F, -4.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(20, 114).mirror().addBox(-0.5F, -1.0F, -3.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(102, 16).mirror().addBox(-2.5F, -1.0F, -5.0F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, -0.05F, -1.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition bone13 = bone12.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offset(1.0F, 16.95F, -2.25F));

        PartDefinition cube_r4 = bone13.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(28, 87).mirror().addBox(0.0F, -4.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(38, 114).mirror().addBox(0.0F, 0.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(112, 119).mirror().addBox(0.0F, 0.0F, -1.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, -1.309F));

        PartDefinition bone16 = bone12.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offset(1.0F, 16.95F, 0.25F));

        PartDefinition cube_r5 = bone16.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(28, 87).mirror().addBox(0.0F, -4.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

        PartDefinition bone17 = bone16.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(38, 114).mirror().addBox(0.0F, 0.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition bone18 = bone17.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(112, 119).mirror().addBox(0.0F, 0.0F, -1.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, -1.309F));

        PartDefinition bone20 = bone12.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 16.85F, -2.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r6 = bone20.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(28, 87).mirror().addBox(0.0F, -4.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

        PartDefinition bone21 = bone20.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(38, 114).mirror().addBox(0.0F, 0.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition bone33 = bone21.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(112, 119).mirror().addBox(0.0F, 0.0F, -1.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, -1.309F));

        PartDefinition bone22 = upper.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(0, 57).addBox(3.0257F, -6.7782F, -7.3986F, 2.0F, 15.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(106, 55).addBox(5.0257F, -2.7782F, -3.3986F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 116).addBox(0.0257F, 5.2218F, -1.3986F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(72, 107).addBox(0.0257F, -2.7782F, -2.3986F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, -7.0F, 0.0F, 0.6575F, -0.05F, -0.121F));

        PartDefinition bone23 = bone22.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(88, 107).addBox(-2.25F, 1.95F, -3.0F, 3.0F, 14.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(28, 93).addBox(-2.0F, 0.95F, -4.0F, 4.0F, 16.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(100, 112).addBox(-2.0F, 15.95F, -4.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(1.5257F, 14.2718F, 1.6014F, 2.3998F, 0.0F, 0.0F));

        PartDefinition cube_r7 = bone23.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(72, 120).addBox(0.0F, -1.5F, 1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(72, 120).addBox(0.0F, -1.5F, -4.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 114).addBox(-3.5F, -1.0F, -3.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(102, 16).addBox(0.5F, -1.0F, -5.0F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.05F, -1.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone24 = bone23.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offset(-1.0F, 16.95F, -2.25F));

        PartDefinition cube_r8 = bone24.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(28, 87).addBox(-1.0F, -4.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

        PartDefinition bone25 = bone24.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(38, 114).addBox(-1.0F, 0.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

        PartDefinition bone26 = bone25.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(112, 119).addBox(-1.0F, 0.0F, -1.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, 1.309F));

        PartDefinition bone27 = bone23.addOrReplaceChild("bone27", CubeListBuilder.create(), PartPose.offset(-1.0F, 16.95F, 0.25F));

        PartDefinition cube_r9 = bone27.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(28, 87).addBox(-1.0F, -4.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

        PartDefinition bone28 = bone27.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(38, 114).addBox(-1.0F, 0.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition bone29 = bone28.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(112, 119).addBox(-1.0F, 0.0F, -1.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, 1.309F));

        PartDefinition bone30 = bone23.addOrReplaceChild("bone30", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 16.85F, -2.0F, 0.0F, -3.1416F, 0.0F));

        PartDefinition cube_r10 = bone30.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(28, 87).addBox(-1.0F, -4.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

        PartDefinition bone31 = bone30.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(38, 114).addBox(-1.0F, 0.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition bone32 = bone31.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(112, 119).addBox(-1.0F, 0.0F, -1.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, 1.309F));

        PartDefinition bone = bone23.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(66, 57).addBox(-1.0F, -26.0F, -1.0F, 2.0F, 38.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-9.0F, -36.0F, -5.0F, 18.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(102, 0).addBox(9.0F, -35.0F, -4.0F, 1.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(34, 57).addBox(10.0F, -37.0F, -6.0F, 4.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(102, 72).addBox(-2.0F, -30.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(102, 0).mirror().addBox(-10.0F, -35.0F, -4.0F, 1.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(34, 57).mirror().addBox(-14.0F, -37.0F, -6.0F, 4.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(64, 109).addBox(-1.0F, 1.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 19.1F, -1.5F, -1.5708F, 0.0F, -1.5708F));

        PartDefinition head = upper.addOrReplaceChild("head", CubeListBuilder.create().texOffs(100, 119).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(74, 86).addBox(-3.5F, -5.0F, -4.0F, 7.0F, 4.0F, 7.0F, new CubeDeformation(-0.3F))
                .texOffs(56, 0).addBox(-7.5F, -7.0F, -3.0F, 15.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 87).addBox(-3.5F, -11.0F, -5.0F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(100, 48).addBox(-3.5F, -8.0F, 2.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(58, 13).addBox(-7.5F, -7.0F, -10.0F, 15.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(62, 81).addBox(4.5F, -12.0F, -9.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 87).addBox(4.5F, -12.0F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, -10.9F, 0.5F));

        PartDefinition bone36 = upper.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(0, 98).addBox(-3.0F, 0.25F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.25F, -8.0F, 2.3562F, 0.0F, -3.1416F));

        PartDefinition bone41 = bone36.addOrReplaceChild("bone41", CubeListBuilder.create().texOffs(100, 97).addBox(-1.0F, -0.1667F, -5.0F, 2.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(20, 110).addBox(-1.0F, -0.6667F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0833F, 0.0F));

        PartDefinition cube_r11 = bone41.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(100, 97).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1667F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition lower = han_d.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(102, 27).addBox(-4.0152F, 3.0F, -1.6737F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
                .texOffs(46, 109).addBox(-1.5152F, -3.5F, -1.1737F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(46, 93).addBox(-3.0152F, 5.0F, -0.6737F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(100, 34).addBox(-7.0152F, 5.0F, -0.6737F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -33.0F, -5.0F));

        PartDefinition bone5 = lower.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(118, 112).addBox(-1.0F, 0.4072F, 11.6845F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(120, 9).addBox(-1.0F, -1.5928F, -1.3155F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 6.0F, 0.0F, -0.3927F, 0.1745F, 0.0F));

        PartDefinition cube_r12 = bone5.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 35).addBox(-3.0F, -3.0F, -8.5F, 6.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8877F, 6.2969F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r13 = bone5.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(118, 75).addBox(-0.5F, -0.75F, 6.25F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.2F))
                .texOffs(12, 116).addBox(-0.5F, -0.75F, 0.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.2F))
                .texOffs(74, 72).addBox(-0.5F, -0.75F, 0.25F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1572F, 0.1845F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.3989F, 13.5705F, 0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r14 = bone6.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(100, 38).addBox(-3.0F, -12.0F, 7.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(46, 97).addBox(-2.0F, -12.0F, 0.0F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 14.5727F, -10.8365F, 0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r15 = bone6.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(74, 57).addBox(-3.0F, -13.0F, -1.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 14.151F, -11.1052F, 0.1309F, 0.0F, 0.0F));

        PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 3.8305F, -12.3535F, 0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r16 = bone7.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(120, 0).addBox(3.5F, -11.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(120, 0).addBox(-0.5F, -11.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, 3.75F, 0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r17 = bone7.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(58, 109).addBox(0.0F, -17.0F, -1.0F, 1.0F, 17.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(58, 109).addBox(-3.0F, -17.0F, -1.0F, 1.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 16.1568F, 7.5536F, 0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r18 = bone7.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(118, 69).addBox(-4.5F, -11.0F, -1.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 8.6645F, 3.5082F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(106, 69).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 15.2505F, 7.131F));

        PartDefinition cube_r19 = bone8.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(118, 112).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition bone2 = lower.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(118, 112).mirror().addBox(-1.0F, 0.4072F, 11.6845F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(120, 9).mirror().addBox(-1.0F, -1.5928F, -1.3155F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0304F, 6.0F, 0.0F, -0.3927F, -0.1745F, 0.0F));

        PartDefinition cube_r20 = bone2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(118, 75).mirror().addBox(-0.5F, -0.75F, 6.25F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.2F)).mirror(false)
                .texOffs(12, 116).mirror().addBox(-0.5F, -0.75F, 0.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.2F)).mirror(false)
                .texOffs(74, 72).mirror().addBox(-0.5F, -0.75F, 0.25F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.1572F, 0.1845F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r21 = bone2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 35).addBox(-3.0F, -3.0F, -8.5F, 6.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8877F, 6.2969F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 3.8989F, 14.0705F, 0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r22 = bone3.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(100, 38).mirror().addBox(-1.0F, -12.0F, 7.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(46, 97).mirror().addBox(0.0F, -12.0F, 0.0F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 13.0F, -11.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r23 = bone3.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(74, 57).mirror().addBox(0.0F, -13.0F, -1.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 12.5783F, -11.2687F, 0.1309F, 0.0F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 2.2578F, -12.5169F, 0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r24 = bone4.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(120, 0).mirror().addBox(-4.5F, -11.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(120, 0).mirror().addBox(-0.5F, -11.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0F, 3.75F, 0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r25 = bone4.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(58, 109).mirror().addBox(-1.0F, -17.0F, -1.0F, 1.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(58, 109).mirror().addBox(2.0F, -17.0F, -1.0F, 1.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 16.1568F, 7.5536F, 0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r26 = bone4.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(118, 69).mirror().addBox(-1.5F, -11.0F, -1.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 8.6645F, 3.5082F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone9 = bone4.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(106, 69).mirror().addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 15.2505F, 7.1309F));

        PartDefinition cube_r27 = bone9.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(118, 112).mirror().addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -800f, 800f);
        headPitch = Mth.clamp(headPitch, -60f, 100);

        this.upper.yRot = headYaw * ((float)Math.PI / 180F);
        this.upper.xRot = headPitch *  ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        han_d.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return han_d;
    }

    @Override
    public void setupAnim(HanD entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, -headPitch);

        this.animateWalk(HanDAnimations.HAND_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.attackAnimationState, HanDAnimations.HAND_MELEE, ageInTicks);
        this.animate(entity.idleAnimationState, HanDAnimations.HAND_IDLE, ageInTicks, 1f);
    }
}
